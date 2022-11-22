package com.sysexevn.sunshinecity.config.batch_excel;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.service.impl.PostReadExcelService;

@Component
public class ExcelImportPostReader extends AbstractItemCountingItemStreamItemReader<PostDTO> {

	private Stream<PostDTO> stream;
	private Iterator<PostDTO> iterator;

	public ExcelImportPostReader() {
		super.setName(this.getClass().getSimpleName());
	}

	@Override
	protected PostDTO doRead() throws Exception {
		return iterator.hasNext() ? iterator.next() : null;
	}

	@Override
	protected void doOpen() throws Exception {
		List<PostDTO> listPost = PostReadExcelService.read("src/main/resources/static/data_test/data_test.xlsx");
		// List<PostDTO> listPost = PostReadExcelService.read("C:\\Users\\duy-anhp\\Desktop\\data.xlsx");
		stream = listPost.stream();
		iterator = stream.iterator();
	}

	@Override
	protected void doClose() throws Exception {
		stream.close();
	}

}
