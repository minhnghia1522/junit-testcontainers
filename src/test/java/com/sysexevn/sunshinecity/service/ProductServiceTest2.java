package com.sysexevn.sunshinecity.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.Result;

import com.sysexevn.sunshinecity.converter.ProductConverter;
import com.sysexevn.sunshinecity.dao.IProductDao;
import com.sysexevn.sunshinecity.dto.ProductDto;
import com.sysexevn.sunshinecity.entity.Product;
import com.sysexevn.sunshinecity.exception.BadRequestException;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.impl.ProductServiceImp;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest2 {

	@InjectMocks
	public ProductServiceImp service;
	@Mock
	private IProductDao productDao;
	@Spy
	private ProductConverter productConverter = Mappers.getMapper(ProductConverter.class);

	ProductDto productDto;
	Product productEnity;

	@Test
	public void callUpdatePriceDefault() {
		// Create mock data
		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(100000)).build();
		productEnity = new Product(1, null, null, null, BigDecimal.valueOf(50000), BigDecimal.valueOf(100000), null,
				null);
		// Behavior definition
		lenient().when(productDao.selectById(anyInt())).thenReturn(Optional.of(productEnity));
		lenient().when(productDao.update(any(Product.class))).thenReturn(new Result<Product>(1, productEnity));
		// Call method
		ProductDto result = service.updateNewPrice(productDto);
		// Check the result
		assertEquals(result.getNewPrice(), productDto.getNewPrice());
	}

	@Test
	public void callUpdateProductNotFound() {
		// Create mock data
		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(100000)).build();
		productEnity = new Product(1, null, null, null, BigDecimal.valueOf(50000), BigDecimal.valueOf(100000), null,
				null);
		// Create mock data
		lenient().when(productDao.selectById(anyInt())).thenReturn(Optional.ofNullable(null));
		// Call method & Check the result
		assertThrows(NotFoundException.class, () -> service.updateNewPrice(productDto));
	}

	@Test
	public void callUpdatePriceLessthanzero() {
		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(-10000)).build();
		// Call method & Check the result
		assertThrows(BadRequestException.class, () -> service.updateNewPrice(productDto));
	}

	@Test
	public void testReadDataFromExcel() throws IOException {
		String fileLocation = "src/main/resources/static/data_test/products.xlsx";
		List<ProductDto> products = service.readDataFromExcel(fileLocation);
		assertNotNull(products);
		assertEquals(2, products.size());
		ProductDto product1 = products.get(0);
		assertEquals(1, product1.getId());
		assertEquals("picture1.jpg", product1.getPicture());
		assertEquals("Title 1", product1.getTitle());
		assertEquals("Description 1", product1.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), product1.getOldPrice());
		assertEquals(BigDecimal.valueOf(50.0), product1.getNewPrice());
		assertEquals("Shop 1", product1.getShopName());
		assertEquals(0L, product1.getVersion());
	}
	
	
	@Test
	public void testExportData() throws IOException {
		// Set up test data
		List<Product> products = new ArrayList<>();
		Product product1 = new Product();
		product1.setId(1);
		product1.setPicture("Picture1");
		product1.setTitle("Title1");
		product1.setDescription("Description1");
		product1.setOldPrice(BigDecimal.valueOf(100.0));
		product1.setNewPrice(BigDecimal.valueOf(50.0));
		product1.setShopName("Shop1");
		Product product2 = new Product();
		product2.setId(2);
		product2.setPicture("Picture2");
		product2.setTitle("Title2");
		product2.setDescription("Description2");
		product2.setOldPrice(BigDecimal.valueOf(200.0));
		product2.setNewPrice(BigDecimal.valueOf(100.0));
		product2.setShopName("Shop2");
		products.add(product1);
		products.add(product2);
		lenient().when(productDao.selectAll()).thenReturn(products);

		String filePath = service.exportData();
		File file = new File(filePath);
		// Verify the results
		assertTrue(file.exists());
		Workbook workbook = WorkbookFactory.create(new File(filePath));
		Sheet sheet = workbook.getSheetAt(0);
		assertEquals(2, sheet.getLastRowNum());
		Row row1 = sheet.getRow(1);
		assertEquals(1, row1.getCell(0).getNumericCellValue(), 0.01);
		assertEquals("Picture1", row1.getCell(1).getStringCellValue());
		assertEquals("Title1", row1.getCell(2).getStringCellValue());
		assertEquals("Description1", row1.getCell(3).getStringCellValue());
		assertEquals(100.0, row1.getCell(4).getNumericCellValue(), 0.01);
		assertEquals(50.0, row1.getCell(5).getNumericCellValue(), 0.01);
		assertEquals("Shop1", row1.getCell(6).getStringCellValue());
		
	    // Verify the results (continued)
	    Row row2 = sheet.getRow(2);
	    assertEquals(2, row2.getCell(0).getNumericCellValue(), 0.01);
	    assertEquals("Picture2", row2.getCell(1).getStringCellValue());
	    assertEquals("Title2", row2.getCell(2).getStringCellValue());
	    assertEquals("Description2", row2.getCell(3).getStringCellValue());
	    assertEquals(200.0, row2.getCell(4).getNumericCellValue(), 0.01);
	    assertEquals(100.0, row2.getCell(5).getNumericCellValue(), 0.01);
	    assertEquals("Shop2", row2.getCell(6).getStringCellValue());
	    workbook.close();
	    file.deleteOnExit();
	}
}
