package com.sysexevn.sunshinecity.poi;

import org.apache.poi.ss.usermodel.Workbook;

public interface PoiAdvance {

	public <T> void read(Workbook workbook, Class<T> clazzz);
}
