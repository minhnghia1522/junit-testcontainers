package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;

public interface IMenuService {
	
	MenuDto createMenu(MenuDto menu);
	
	List<Menu> saveAll(List<Menu> menus);
	
	MenuDto updateMenu(MenuDto menu);
	
	MenuDto deleteMenu(MenuDto menu);

	MenuDto getById(Integer id);

	List<MenuDto> getAll();
}
