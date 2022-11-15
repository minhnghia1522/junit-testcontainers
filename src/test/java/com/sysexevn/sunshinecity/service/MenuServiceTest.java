package com.sysexevn.sunshinecity.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.sysexevn.sunshinecity.config.AbsTest;
import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MenuServiceTest extends AbsTest {

	@Autowired
	public IMenuService service;

	@DisplayName("Test-Create-Menu")
	@Test
	@Order(1)
	public void testCreate() {
		MenuDto menu = new MenuDto();
		menu.setMenuName("co111");
		menu.setMenuPath("localhost:3000/co111");
		menu.setCreatedAt(new Date());
		menu.setUpdateAt(new Date());
		int result = service.createMenu(menu);
		assertEquals(1, result);

	}

	@DisplayName("Test-Get-By-Id")
	@Test
	@Order(2)
	public void testGetById() {
		Menu menu = new Menu();
		menu.setMenuName("co111");
		menu.setMenuPath("localhost:3000/co111");
		menu.setCreatedAt(new Date());
		menu.setUpdateAt(new Date());

		MenuDto dto = service.getById(1);

		assertEquals(menu.getMenuName(), dto.getMenuName());
	}

	@DisplayName("Test-Get-List")
	@Test
	@Order(3)
	public void testGetList() {
		MenuDto menu = new MenuDto();
		menu.setMenuName("CO111");
		service.createMenu(menu);

		List<MenuDto> listDtoes = service.getAll();
		assertEquals(2, listDtoes.size());
	}

	@DisplayName("Test-Get-Id-Not-Found")
	@Test
	@Order(4)
	public void testGetIdNotFound() {
		assertThrows(NotFoundException.class, () -> service.getById(55));
	}

}
