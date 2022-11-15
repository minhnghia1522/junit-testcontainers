package com.sysexevn.sunshinecity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(menu);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<MenuDto>> getAll() {
		List<MenuDto> menu = service.getAll();
		return ResponseEntity.ok(menu);
	}
	
	@PostMapping("/addmenu")
	public ResponseEntity<Void> create(@RequestBody MenuDto dto) {
		service.createMenu(dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody MenuDto dto) {
		service.deleteMenu(dto);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/updatemenu")
	public ResponseEntity<Void> update(@RequestBody MenuDto dto) {
		service.updateMenu(dto);
		return ResponseEntity.ok().build();
	}
	
//	
//	
}
