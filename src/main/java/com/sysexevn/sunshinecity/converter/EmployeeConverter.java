package com.sysexevn.sunshinecity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.dto.SignUpDto;
import com.sysexevn.sunshinecity.entity.Employee;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeConverter {

	EmployeeConverter MAPPER = Mappers.getMapper(EmployeeConverter.class);

	Employee convert(EmployeeDto sourceOrder);

	EmployeeDto convert(Employee sourceCode);

	Employee convert(SignUpDto sourceOrder);
}
