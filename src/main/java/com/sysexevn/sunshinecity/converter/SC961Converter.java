package com.sysexevn.sunshinecity.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.SC961Dto;
import com.sysexevn.sunshinecity.entity.SC961;

@Mapper(componentModel = "spring")
public interface SC961Converter {

	SC961Converter MAPPER = Mappers.getMapper(SC961Converter.class);

	SC961 convert(SC961Dto sourceOrder);

	SC961Dto convert(SC961 sourceCode);

	List<SC961Dto> convert(List<SC961> sourceCode);
}
