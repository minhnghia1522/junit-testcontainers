package com.sysexevn.sunshinecity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.entity.Menu;
import com.sysexevn.sunshinecity.service.IMenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	public IMenuService service;

	@GetMapping("/{id}")
	public ResponseEntity<MenuDto> getById(@PathVariable("id") int id) {
		MenuDto menu = service.getById(id);
		if (menu == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(menu);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<MenuDto>> getAll() {
		List<MenuDto> menu = service.getAll();
		return ResponseEntity.ok(menu);
	}

	@PostMapping("/addmenu")
	public ResponseEntity<MenuDto> create(@RequestBody MenuDto dto) {
		MenuDto menu = service.createMenu(dto);
		return ResponseEntity.ok(menu);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		return ResponseEntity.ok(service.deleteMenu(id));
	}

	@PutMapping("/updatemenu")
	public ResponseEntity<MenuDto> update(@RequestBody MenuDto dto) {
		MenuDto menu = service.updateMenu(dto);
		return ResponseEntity.ok(menu);
	}

	@PostMapping("/CreatAll")
	public ResponseEntity<Void> createAll(@RequestBody List<Menu> listMenu) {
		service.saveAll(listMenu);
		return ResponseEntity.ok().build();
	}
}
