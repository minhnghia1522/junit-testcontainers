package com.sysexevn.sunshinecity.service.impl;

import java.io.File;
import java.util.List;

import com.poiji.bind.Poiji;
import com.poiji.bind.mapping.PoijiNumberFormat;
import com.poiji.option.PoijiOptions;
import com.sysexevn.sunshinecity.dto.PostDTOExcel;

public class ExcelAutoMapper {

	public static void read() {
		
		PoijiNumberFormat numberFormat = new PoijiNumberFormat();
		numberFormat.putNumberFormat((short) 1, "mm/dd/yyyy hh.mm aa");
		PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().poijiNumberFormat(numberFormat).build();
		List<PostDTOExcel> lists = Poiji.fromExcel(new File("C:\\Users\\duy-anhp\\Desktop\\data_test.xlsx"),
				PostDTOExcel.class, options);
		for (PostDTOExcel dto : lists) {
			System.out.println(dto);
		}
		
	}

	public static void main(String[] args) {
		ExcelAutoMapper.read();
	}
}
