package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.MenuConverter;
import com.sysexevn.sunshinecity.dao.IMenuDao;
import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;
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
		return converter.convert(result.getEntity());
	}

	public MenuDto deleteMenu(MenuDto menu) {
		Menu domain = converter.convert(menu);
		Result<Menu> result = menuDao.delete(domain);
		return converter.convert(result.getEntity());
	}

	public MenuDto getById(Integer id) {
		Optional<Menu> menuResult = menuDao.findById(id);
		if (menuResult.isEmpty()) {
			return null;
		}
		return converter.convert(menuResult.get());
	}

	public List<MenuDto> getAll() {
		List<MenuDto> listMenuDto = new ArrayList<>();
		menuDao.findAllMenu().forEach(menu -> listMenuDto.add(converter.convert(menu)));
		return listMenuDto;
	}

	public List<Menu> saveAll(List<Menu> menus) {
		menuDao.insertAll(menus);
		return menus;
	}

}
