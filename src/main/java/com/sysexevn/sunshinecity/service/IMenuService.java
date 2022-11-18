package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.entity.Menu;

public interface IMenuService {
	
	MenuDto createMenu(MenuDto menu);
	
	List<MenuDto> saveAll(List<MenuDto> menus);
	
	MenuDto updateMenu(MenuDto menu);
	
	int deleteMenu(Integer id);

	MenuDto getById(Integer id);

	List<MenuDto> getAll();
}
