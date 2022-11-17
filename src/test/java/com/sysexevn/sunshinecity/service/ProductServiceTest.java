package com.sysexevn.sunshinecity.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seasar.doma.jdbc.Result;

import com.sysexevn.sunshinecity.converter.ProductConverter;
import com.sysexevn.sunshinecity.dao.IProductDao;
import com.sysexevn.sunshinecity.domain.Product;
import com.sysexevn.sunshinecity.dto.ProductDto;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.impl.ProductServiceImp;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	public ProductServiceImp service;
	@Mock
	private IProductDao productDao;
	@Mock
	private ProductConverter converter;

	List<Product> list = new ArrayList<Product>();

	ProductDto productDto;

	Product productEnity;

	@BeforeEach
	public void setup() {
		productDto = mock(ProductDto.class);
		productEnity = mock(Product.class);

		lenient().when(converter.convert(productDto)).thenReturn(productEnity);
		lenient().when(converter.convert(productEnity)).thenReturn(productDto);

		Product product = new Product();
		product.setId(1);
		product.setTitle("Bún đậu");
		product.setPicture("https://img.com/1.jpg");
		product.setDescription("Món ăn quốc dân");
		product.setOldPrice(BigDecimal.valueOf(10000));
		product.setNewPrice(BigDecimal.valueOf(50000));
		product.setShopName("Minh Nghĩa");
		list.add(product);

		product.setId(2);
		product.setTitle("Bún Bòa Huế");
		product.setPicture("https://img.com/2.jpg");
		product.setDescription("Món ăn quốc dân");
		product.setOldPrice(BigDecimal.valueOf(10000));
		product.setNewPrice(BigDecimal.valueOf(45000));
		product.setShopName("Bún Bò 69");
		list.add(product);

		product.setId(3);
		product.setTitle("Bánh xèo");
		product.setPicture("https://img.com/3.jpg");
		product.setDescription("Món ăn quốc dân");
		product.setOldPrice(BigDecimal.valueOf(25000));
		product.setNewPrice(BigDecimal.valueOf(35000));
		product.setShopName("Bánh xèo 22");
		list.add(product);
	}

	/**
	 * Get full data from database
	 */
	@Test
	public void callGetList() {
		lenient().when(productDao.selectAll()).thenReturn(list);
		List<ProductDto> result = service.getList();
		assertEquals(result.size(), 3);
	}

	@Test
	public void callSave() {
		lenient().when(productDao.insert(productEnity)).thenReturn(new Result<Product>(1, productEnity));

		ProductDto result = service.save(productDto);
		assertNotNull(result);

	}

	@Test
	public void callSelectById() {
		lenient().when(productDao.selectById(Integer.valueOf(1))).thenReturn(Optional.of(productEnity));
		ProductDto result = service.getById(1);
		assertNotNull(result);
	}

	@Test
	public void callUpdate() {
		lenient().when(productDao.update(productEnity)).thenReturn(new Result<Product>(1, productEnity));
		ProductDto result = service.update(productDto);
		assertNotNull(result);
	}

	@Test
	public void callDelete() {
		lenient().when(productDao.delete(productEnity)).thenReturn(new Result<Product>(1, productEnity));
		service.delete(productDto);
	}

	@Test
	public void callSelectByIdExceptionNotFound() {
		lenient().when(converter.convert(productEnity)).thenReturn(productDto);
		lenient().when(productDao.update(productEnity)).thenReturn(new Result<Product>(0, productEnity));
		assertThrows(NotFoundException.class, () -> service.update(productDto));

	}

	@Test
	public void callDeleteExceptionNotFound() {
		lenient().when(productDao.delete(productEnity)).thenReturn(new Result<Product>(0, productEnity));
		assertThrows(NotFoundException.class, () -> service.delete(productDto));

	}

}
