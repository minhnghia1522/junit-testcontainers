package com.sysexevn.sunshinecity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.ProductDto;
import com.sysexevn.sunshinecity.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductConverter {
	ProductConverter MAPPER = Mappers.getMapper(ProductConverter.class);

	Product convert(ProductDto sourceOrder);

	ProductDto convert(Product sourceCode);
}
