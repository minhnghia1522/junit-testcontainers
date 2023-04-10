package com.sysexevn.sunshinecity.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.DepartmentDto;
import com.sysexevn.sunshinecity.entity.Department;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentConverter {
	
	DepartmentConverter MAPPER = Mappers.getMapper(DepartmentConverter.class);

	Department convert(DepartmentDto sourceOrder);

	DepartmentDto convert(Department sourceCode);

	List<Department> convertListEntity(List<DepartmentDto> sourceOrder);
	
	List<DepartmentDto> convertListDto(List<Department> sourceOrder);
}
