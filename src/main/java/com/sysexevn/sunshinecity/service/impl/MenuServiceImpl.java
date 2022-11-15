package com.sysexevn.sunshinecity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dao.IMenuDao;
import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuDao menuDao;

	public int createMenu(MenuDto menuDto) {
		Menu domain = new Menu();
		BeanUtils.copyProperties(menuDto, domain);
		return menuDao.insert(domain);
	}
	public int updateMenu(MenuDto menuDto) {
		Menu domain = new Menu();
		BeanUtils.copyProperties(menuDto, domain);
		return menuDao.update(domain);
	}
	
	public int deleteMenu(MenuDto menuDto) {
		Menu domain = new Menu();
		BeanUtils.copyProperties(menuDto, domain);
		return menuDao.delete(domain);
	}

	public int[] saveAll(List<Menu> menu) {
		return menuDao.insertAll(menu);
	}
	

	public MenuDto getById(Integer id) {
		Optional<Menu> menuResult = menuDao.findById(id);
		if (menuResult.isEmpty()) {
			throw new NotFoundException();
		}
		return menuResult.get().toDto();
	}

	public List<MenuDto> getAll() {
		List<MenuDto> listMenuDto = new ArrayList<>();
		menuDao.findAllMenu().forEach(menu -> listMenuDto.add(menu.toDto()));
		return listMenuDto;
	}

}
