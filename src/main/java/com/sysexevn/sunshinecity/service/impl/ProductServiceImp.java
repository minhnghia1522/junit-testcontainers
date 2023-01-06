package com.sysexevn.sunshinecity.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.ProductConverter;
import com.sysexevn.sunshinecity.dao.IProductDao;
import com.sysexevn.sunshinecity.dto.ProductDto;
import com.sysexevn.sunshinecity.entity.Product;
import com.sysexevn.sunshinecity.exception.BadRequestException;
import com.sysexevn.sunshinecity.exception.NotFoundException;
import com.sysexevn.sunshinecity.service.IProductService;

@Service
public class ProductServiceImp implements IProductService {

	@Autowired
	private IProductDao repository;

	@Autowired
	private ProductConverter mapper;

	@Override
	public ProductDto save(ProductDto pDto) {
		Product product = mapper.convert(pDto);
		Product result = repository.insert(product).getEntity();
		return mapper.convert(result);
	}

	@Override
	public ProductDto getById(int id) {
		Product result = repository.selectById(id).orElseThrow(NotFoundException::new);
		return mapper.convert(result);

	}

	@Override
	public List<ProductDto> getList() {
		List<Product> results = repository.selectAll();
		List<ProductDto> productDtoes = results.stream().map(p -> mapper.convert(p)).collect(Collectors.toList());
		return productDtoes;
	}

	@Override
	public ProductDto update(ProductDto pDto) {
		Result<Product> result = repository.update(mapper.convert(pDto));
		if (result.getCount() > 0) {
			return mapper.convert(result.getEntity());
		}
		throw new NotFoundException();
	}

	@Override
	public void delete(ProductDto pDto) {
		Result<Product> result = repository.delete(mapper.convert(pDto));
		if (result.getCount() < 1) {
			throw new NotFoundException();
		}
	}

	@Override
	public ProductDto updateNewPrice(ProductDto newProductDto) {
		// Check price must greater > 0
		if (newProductDto.getNewPrice().compareTo(BigDecimal.ZERO) < 0) {
			throw new BadRequestException();
		}
		Optional<Product> oldProductOptional = repository.selectById(newProductDto.getId());
		if (oldProductOptional.isEmpty()) {
			throw new NotFoundException();
		}
		Product product = oldProductOptional.get();
		newProductDto.setOldPrice(product.getNewPrice());
		// update
		Product productEntity = mapper.convert(newProductDto);
		Product ressult = repository.update(productEntity).getEntity();
		return mapper.convert(ressult);
	}

	@Override
	public String exportData() throws IOException {
		// Tạo tập tin Excel và sheet
		Workbook workbook = new XSSFWorkbook();
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("#,##0.00"));
		Sheet sheet = workbook.createSheet("Product");
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 25000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 15000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("ID");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("PICTURE");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("TITLE");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(3);
		headerCell.setCellValue("DESCRIPTION");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(4);
		headerCell.setCellValue("OLD PRICE");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(5);
		headerCell.setCellValue("NEW PRICE");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(6);
		headerCell.setCellValue("SHOP NAME");
		headerCell.setCellStyle(headerStyle);

		List<Product> products = repository.selectAll();
		// Duyệt qua tất cả các bản ghi trong danh sách dữ liệu
		int rowNum = 1;
		for (Product p : products) {
			Row row = sheet.createRow(rowNum++);
			// Lấy dữ liệu của từng cột trong bản ghi hiện tại và ghi vào các ô trong dòng
			row.createCell(0).setCellValue(p.getId());
			row.createCell(1).setCellValue(p.getPicture());
			row.createCell(2).setCellValue(p.getTitle());
			row.createCell(3).setCellValue(p.getDescription());
			row.createCell(4).setCellStyle(cellStyle);
			row.createCell(4).setCellValue(Double.parseDouble(p.getOldPrice().toString()));
			row.createCell(5).setCellStyle(cellStyle);
			row.createCell(5).setCellValue(Double.parseDouble(p.getNewPrice().toString()));
			row.createCell(6).setCellValue(p.getShopName());
		}
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();;
		String fileLocation = path.substring(0, path.length() - 1) + "Products.xlsx";

		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

		return fileLocation;
	}

	@Override
	public List<ProductDto> readDataFromExcel(String fileLocation) throws IOException {
		// Đọc dữ liệu từ tập tin Excel bằng Apache POI
		Workbook workbook = WorkbookFactory.create(new FileInputStream(fileLocation));
		Sheet sheet = workbook.getSheetAt(0);
		List<ProductDto> products = new ArrayList<>();
		// Duyệt qua tất cả các dòng trong sheet
		for (Row row : sheet) {
			// Bỏ qua dòng đầu tiên (dòng tiêu đề)
			if (row.getRowNum() == 0) {
				continue;
			}
			// Tạo một đối tượng ProductDto mới
			ProductDto productDto = new ProductDto();
			// Lấy dữ liệu từ các ô trong dòng hiện tại và set vào đối tượng
			productDto.setId(Double.valueOf(row.getCell(0).getNumericCellValue()).intValue());
			productDto.setPicture(row.getCell(1).getStringCellValue());
			productDto.setTitle(row.getCell(2).getStringCellValue());
			productDto.setDescription(row.getCell(3).getStringCellValue());
			productDto.setOldPrice(BigDecimal.valueOf(row.getCell(4).getNumericCellValue()));
			productDto.setNewPrice(BigDecimal.valueOf(row.getCell(5).getNumericCellValue()));
			productDto.setShopName(row.getCell(6).getStringCellValue());
			productDto.setVersion(0L);
			products.add(productDto);
		}
		workbook.close();
		return products;
	}
}
