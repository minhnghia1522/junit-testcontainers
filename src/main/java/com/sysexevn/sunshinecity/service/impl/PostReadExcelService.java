package com.sysexevn.sunshinecity.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dto.PostDTO;

@Service
public class PostReadExcelService {

	public static List<PostDTO> read(String fileName) throws IOException {
		List<PostDTO> list = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream(new File(fileName));
		// XSSFWorkbook
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); // step header
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				PostDTO dto = new PostDTO();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
						// id auto generate
//						case 0: {
//							if (cell.getCellType() == CellType.NUMERIC) {
//								dto.setId((int) cell.getNumericCellValue());
//								System.out.print(dto.getId());
//							}
//							break;
//						}
						case 1: {
							if (cell.getCellType() == CellType.STRING) {
								dto.setTitle(cell.toString());
								//System.out.print(" - " + dto.getTitle());
							}
							break;
						}
						case 2: {
							if (cell.getCellType() == CellType.STRING) {
								dto.setPostName(cell.toString());
								//System.out.print(" - " + dto.getPostName());
							}
							break;
						}
						case 3: {
							if (cell.getCellType() == CellType.STRING) {
								dto.setPostDescription(cell.toString());
								//System.out.print(" - " + dto.getPostDescription());
							}
							break;
						}
						case 4: {
							if (cell.getCellType() == CellType.STRING) {
								@SuppressWarnings("deprecation")
								Date date = new Date(cell.getStringCellValue());
								// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
								dto.setCreatedAt(date);
								//System.out.print(" - " + dto.getCreatedAt());
							}
							break;
						}
						case 5: {
							if (cell.getCellType() == CellType.STRING) {
								@SuppressWarnings("deprecation")
								Date date = new Date(cell.getStringCellValue());
								dto.setUpdatedAt(date);
								//System.out.print(" - " + dto.getUpdatedAt());
							}
							break;
						}
						case 6: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setPostTypeId((int) cell.getNumericCellValue());
							}
							break;
						}
						case 7: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setAcceptedAnswerId((int) cell.getNumericCellValue());
							}
							break;
						}
						case 8: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setParentId((int) cell.getNumericCellValue());
							}
							break;
						}
						case 9: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setScore((int) cell.getNumericCellValue());
							}
							break;
						}
						case 10: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setViewCount((int) cell.getNumericCellValue());
							}
							break;
						}
						case 11: {
							if (cell.getCellType() == CellType.STRING) {
								dto.setBody(cell.toString());
							}
							break;
						}
						case 12: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setOwnerUserId((int) cell.getNumericCellValue());
							}
							break;
						}
						case 13: {
							if (cell.getCellType() == CellType.STRING) {
								dto.setOwnerDisplayName(cell.toString());
							}
							break;
						}
						case 14: {
							if (cell.getCellType() == CellType.STRING) {
								dto.setTags(cell.toString());
							}
							break;
						}
						case 15: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setAnswerCount((int) cell.getNumericCellValue());
							}
							break;
						}
						case 16: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setCommentCount((int) cell.getNumericCellValue());
							}
							break;
						}
						case 17: {
							if (cell.getCellType() == CellType.NUMERIC) {
								dto.setFavoriteCount((int) cell.getNumericCellValue());
							}
							break;
						}
						case 18: {
							if (cell.getCellType() == CellType.STRING) {
								@SuppressWarnings("deprecation")
								Date date = new Date(cell.getStringCellValue());
								dto.setClosedDate(date);
							}
							break;
						}
						case 19: {
							if (cell.getCellType() == CellType.STRING) {
								@SuppressWarnings("deprecation")
								Date date = new Date(cell.getStringCellValue());
								dto.setCommunityOwnerDate(date);
							}
							break;
						}
						
					}
				}
				list.add(dto);
				//System.out.println("\n");
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		List<PostDTO> result = read("C:\\Users\\duy-anhp\\Desktop\\data.xlsx");
		System.out.println(result);
	}
}
