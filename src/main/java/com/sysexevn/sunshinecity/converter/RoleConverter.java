package com.sysexevn.sunshinecity.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.RoleDto;
import com.sysexevn.sunshinecity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleConverter {
	RoleConverter MAPPER = Mappers.getMapper(RoleConverter.class);

	Role convert(RoleDto sourceOrder);

	RoleDto convert(Role sourceCode);

	List<RoleDto> convert(List<Role> sourceCode);
}
