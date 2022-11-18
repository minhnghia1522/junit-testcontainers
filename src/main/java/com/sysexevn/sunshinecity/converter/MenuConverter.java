package com.sysexevn.sunshinecity.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.MenuDto;
import com.sysexevn.sunshinecity.entity.Menu;

@Mapper(componentModel = "spring")
public interface MenuConverter {

	MenuConverter MAPPER = Mappers.getMapper(MenuConverter.class);

	Menu convert(MenuDto sourceOrder);

	MenuDto convert(Menu sourceCode);

	List<Menu> convertListEntity(List<MenuDto> sourceOrder);
	
	List<MenuDto> convertListDto(List<Menu> sourceOrder);

}
