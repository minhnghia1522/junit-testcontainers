package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.MenuConverter;
import com.sysexevn.sunshinecity.dao.IMenuDao;
import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.entity.Menu;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuDao menuDao;

	@Autowired
	private MenuConverter converter;

	public MenuDto createMenu(MenuDto menu) {
		Menu domain = converter.convert(menu);
		Result<Menu> result = menuDao.insert(domain);
		return converter.convert(result.getEntity());
	}

	public MenuDto updateMenu(MenuDto menuDto) {
		Menu domain = converter.convert(menuDto);
		Result<Menu> result = menuDao.update(domain);
		if (result.getCount() > 0) {
			return converter.convert(result.getEntity());
		}
		throw new NotFoundException();
	}

	public int deleteMenu(Integer id) {
		return  menuDao.delete(id);
	}

	public MenuDto getById(Integer id) {
		Optional<Menu> menuResult = menuDao.findById(id);
		if (menuResult.isEmpty()) {
			throw new NotFoundException();
		}
		return converter.convert(menuResult.get());
	}

	public List<MenuDto> getAll() {
		List<MenuDto> listMenuDto = new ArrayList<>();
		menuDao.findAllMenu().forEach(menu -> listMenuDto.add(converter.convert(menu)));
		return listMenuDto;
	}

	public List<MenuDto> saveAll(List<MenuDto> menus) {
		List<Menu> menuEntitis = converter.convertListEntity(menus);
		BatchResult<Menu> batchResult = menuDao.insertAll(menuEntitis);
		return converter.convertListDto(batchResult.getEntities());
	}

}
