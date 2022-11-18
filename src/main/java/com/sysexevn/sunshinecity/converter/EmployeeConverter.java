package com.sysexevn.sunshinecity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.domain.Employee;
import com.sysexevn.sunshinecity.dto.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeConverter {

	EmployeeConverter MAPPER = Mappers.getMapper(EmployeeConverter.class);

	Employee convert(EmployeeDto sourceOrder);

	EmployeeDto convert(Employee sourceCode);

}
