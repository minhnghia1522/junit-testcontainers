package com.sysexevn.sunshinecity.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;

import com.sysexevn.sunshinecity.converter.MenuConverter;
import com.sysexevn.sunshinecity.dao.IMenuDao;
import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.entity.Menu;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.impl.MenuServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

	@InjectMocks
	public MenuServiceImpl service;

	@Mock
	private IMenuDao menuDao;

	@Spy
	private MenuConverter menuConverter = Mappers.getMapper(MenuConverter.class);

	private Menu menu;

	private MenuDto menuDto;

	@BeforeEach
	public void setupdata() {
		menu = new Menu();
		menu.setMenuName("Bussiness");
		menu.setMenuPath("co111");

		menuDto = new MenuDto();
		menuDto.setMenuName("Bussiness");
		menuDto.setMenuPath("co111");

		lenient().when(menuConverter.convert(menu)).thenReturn(menuDto);
		lenient().when(menuConverter.convert(menuDto)).thenReturn(menu);
	}

	@DisplayName("Test-Create-Menu")
	@Test
	public void testCreate() {
		// given - precondition or setup
		when(menuDao.insert(any(Menu.class))).thenReturn(new Result<Menu>(1, menu));

		// when - action or the behavior that we are going test
		MenuDto savedMenu = service.createMenu(menuDto);

		// then - verify the output
		assertNotNull(savedMenu);
	}

	@DisplayName("Test-Get-By-Id")
	@Test
	public void testGetById() {
		when(menuDao.findById(anyInt())).thenReturn(Optional.of(menu));
		MenuDto result = service.getById(1);
		assertNotNull(result);
		assertEquals(menu.getMenuName(), result.getMenuName());
	}

	@DisplayName("Test-Get-All")
	@Test
	public void testGetAll() {
		when(menuDao.findAllMenu()).thenReturn(List.of(menu));
		List<MenuDto> result = service.getAll();
		assertNotNull(result);
		assertEquals(result.size(), 1);
	}

	@DisplayName("Test-Save-All")
	@Test
	public void testSaveAll() {
		when(menuDao.insertAll(anyList())).thenReturn(new BatchResult<Menu>(new int[1], List.of(menu)));
		List<MenuDto> result = service.saveAll(List.of(menuDto));
		assertNotNull(result);
		assertEquals(result.size(), 1);
	}

	@DisplayName("Test-Get-By-Id-Fail")
	@Test
	public void testGetById_Fail() {
		when(menuDao.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> service.getById(15));
	}

	@DisplayName("Test-Update")
	@Test
	public void testUpdate() {
		Menu menu = new Menu();
		menu.setMenuName("Market");
		menu.setMenuPath("bc211");
		when(menuDao.update(any(Menu.class))).thenReturn(new Result<Menu>(1, menu));
		MenuDto result = service.updateMenu(menuDto);
		assertNotNull(result);
		assertEquals(result.getId(), menu.getId());
		assertEquals(result.getMenuName(), menu.getMenuName());
	}

	@DisplayName("Test-Update-Fail")
	@Test
	public void testUpdate_Fail() {
		when(menuDao.update(any(Menu.class))).thenReturn(new Result<Menu>(0, menu));
		assertThrows(NotFoundException.class, () -> service.updateMenu(menuDto));
	}

	@DisplayName("Test-Delete")
	@Test
	public void testDelete() {
		when(menuDao.delete(anyInt())).thenReturn(1);
		assertEquals(1, service.deleteMenu(5));
	}

	@DisplayName("Test-Delete-Fail")
	@Test
	public void testDelete_Fail() {
		when(menuDao.delete(anyInt())).thenReturn(0);
		assertEquals(0, service.deleteMenu(2));
	}
}
