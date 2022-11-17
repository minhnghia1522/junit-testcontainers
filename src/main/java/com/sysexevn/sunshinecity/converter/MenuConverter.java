package com.sysexevn.sunshinecity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.domain.Menu;
import com.sysexevn.sunshinecity.dto.MenuDto;

@Mapper(componentModel = "spring")
public interface MenuConverter {

	MenuConverter MAPPER = Mappers.getMapper(MenuConverter.class);

	Menu convert(MenuDto sourceOrder);

	MenuDto convert(Menu sourceCode);

}
