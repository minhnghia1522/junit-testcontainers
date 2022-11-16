package com.sysexevn.sunshinecity.poi.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sysexevn.sunshinecity.annotation.SheetName;
import com.sysexevn.sunshinecity.poi.PoiAdvance;

@Service
public class PoiAdvanceImpl implements PoiAdvance {
//	private static final Logger logger = LoggerFactory.getLogger(PoiAdvanceImpl.class);

	@Override
	public <T> void read(Workbook workbook, Class<T> clazzz) {
		Sheet sheet = this.getSheet(workbook, clazzz);
		Field[] fields = clazzz.getDeclaredFields();
		Iterator<Row> rowIterator = sheet.iterator();

		// read header
		Row rowHeader = rowIterator.next();
		List<String> headers = this.readHeader(rowHeader);

		List<T> list = new ArrayList<>();

		while (rowIterator.hasNext()) {
			try {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				T dataRow = clazzz.getDeclaredConstructor().newInstance();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("ok");
	}

	private <T> Sheet getSheet(Workbook workbook, Class<T> clazzz) {
		Sheet sheet;
		SheetName sheetName = clazzz.getAnnotation(SheetName.class);
		if (ObjectUtils.isEmpty(sheetName)) {
			sheet = workbook.getSheetAt(0);
		} else {
			sheet = workbook.getSheet(sheetName.value());
		}
		return sheet;
	}

	private List<String> readHeader(Row row) {
		List<String> list = new ArrayList<>();
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getCellType() == CellType.STRING) {
				list.add(cell.getStringCellValue());
			}
		}
		return list;
	}

}
