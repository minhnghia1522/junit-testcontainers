package com.sysexevn.sunshinecity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.Result;
import org.springframework.test.util.ReflectionTestUtils;

import com.sysexevn.sunshinecity.converter.ProductConverter;
import com.sysexevn.sunshinecity.converter.ProductConverterImpl;
import com.sysexevn.sunshinecity.dao.IProductDao;
import com.sysexevn.sunshinecity.dto.ProductDto;
import com.sysexevn.sunshinecity.entity.Product;
import com.sysexevn.sunshinecity.service.impl.ProductServiceImp;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest2 {

	@InjectMocks
	public ProductServiceImp service;

	@Mock
	private IProductDao productDao;

	ProductDto productDto;

	Product productEnity;

	@Spy
	private ProductConverter productConverter = Mappers.getMapper(ProductConverter.class);

	@Before
	void before() {
		ReflectionTestUtils.setField(productConverter, "mapper", new ProductConverterImpl());
	}

	@Test
	public void callUpdatePriceDefault() {
		// Tạo mock data
		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(100000)).build();
		productEnity = new Product(1, null, null, null, BigDecimal.valueOf(50000), BigDecimal.valueOf(100000), null);

		// Định nghĩa hành vi
		lenient().when(productDao.selectById(anyInt())).thenReturn(Optional.of(productEnity));
		lenient().when(productDao.update(any(Product.class))).thenReturn(new Result<Product>(1, productEnity));
//		lenient().when(converter.convert(productDto)).thenReturn(productEnity);
//		lenient().when(converter.convert(productEnity)).thenReturn(productDto);

		// Gọi method
		ProductDto result = service.updateNewPrice(productDto);
		// Kiểm tra kết quả
		assertEquals(result.getNewPrice(), productDto.getNewPrice());
		assertEquals(result.getOldPrice(), productEnity.getNewPrice());
	}

//	@Test
//	public void callUpdatePriceProductNotFound() {
//		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(100000)).build();
//		productEnity = new Product(1, null, null, null, BigDecimal.valueOf(50000), BigDecimal.valueOf(100000), null);
//		// Định nghĩa hành vi
//		lenient().when(productDao.selectById(1)).thenReturn(Optional.ofNullable(null));
//		lenient().when(converter.convert(productDto)).thenReturn(productEnity);
//		lenient().when(converter.convert(productEnity)).thenReturn(productDto);
//
//		// Gọi method & Kiểm tra kết quả
//		assertThrows(NotFoundException.class, () -> service.updateNewPrice(productDto));
//	}
//
//	@Test
//	public void callUpdatePriceLessthanzero() {
//		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(-1)).build();
//		// Gọi method & Kiểm tra kết quả
//		assertThrows(BadRequestException.class, () -> service.updateNewPrice(productDto));
//	}
//
//	@Test
//	public void callUpdatePriceOldIsNull() {
//		// Tạo mock data
//		productDto = ProductDto.builder().id(1).newPrice(BigDecimal.valueOf(100000)).build();
//		productEnity = new Product(1, null, null, null, BigDecimal.valueOf(50000), BigDecimal.valueOf(12000), null);
//		Product productReturn = new Product(1, null, null, null, BigDecimal.valueOf(50000), BigDecimal.valueOf(12000), null);
//		// Định nghĩa hành vi
//		lenient().when(productDao.selectById(Integer.valueOf(1))).thenReturn(Optional.of(productEnity));
//		lenient().when(productDao.updateNewPrice(productEnity)).thenReturn(new Result<Product>(1, productReturn));
//		lenient().when(converter.convert(productDto)).thenReturn(productEnity);
//		lenient().when(converter.convert(productEnity)).thenReturn(productReturn);
//
//		// Gọi method
//		ProductDto result = service.updateNewPrice(productDto);
//		// Kiểm tra kết quả
//		assertEquals(result.getNewPrice(), productDto.getNewPrice());
//		assertEquals(result.getOldPrice(), productEnity.getNewPrice());
//	}

}
