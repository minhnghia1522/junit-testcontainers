package com.sysexevn.sunshinecity.service;

import java.util.List;


import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;

public interface IMenuService {
	
	int createMenu(MenuDto menu);
	
	int updateMenu(MenuDto menu);
	
	int deleteMenu(MenuDto menu);

	int[] saveAll(List<Menu> menu);

	MenuDto getById(Integer id);

	List<MenuDto> getAll();
}
