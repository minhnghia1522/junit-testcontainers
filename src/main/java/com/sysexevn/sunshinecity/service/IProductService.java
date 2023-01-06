package com.sysexevn.sunshinecity.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.sysexevn.sunshinecity.dto.ProductDto;

public interface IProductService {

	ProductDto save(ProductDto pDto);

	ProductDto getById(int id);

	List<ProductDto> getList();
	
	ProductDto update(ProductDto pDto);
	
	void delete(ProductDto pDto);
	
	String exportData() throws FileNotFoundException, IOException;
	
	List<ProductDto> readDataFromExcel(String fileLocation) throws FileNotFoundException, IOException;

	ProductDto updateNewPrice(ProductDto newProductDto);

}
