package com.sysexevn.sunshinecity.domain;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.springframework.beans.BeanUtils;

import com.sysexevn.sunshinecity.dto.MenuDto;

import lombok.Data;

@Entity
@Data
@Table(name = "menu")
public class Menu {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_path")
	private String menuPath;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "update_at")
	private Date updateAt;

	public Menu(Integer menuId, String menuName, String menuPath, Date createdAt, Date updateAt) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPath = menuPath;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public Menu() {
		super();
	}

	public MenuDto toDto() {
		var result = new MenuDto();
		BeanUtils.copyProperties(this, result);
		return result;
	}
}
