package com.sysexevn.sunshinecity.batch.csv;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.service.impl.PostReadCSVService;

@Component
public class CsvImportPostReader extends AbstractItemCountingItemStreamItemReader<PostDTO> {

	private Stream<PostDTO> stream;
	private Iterator<PostDTO> iterator;
	private String path;

	public CsvImportPostReader() {
		super.setName(this.getClass().getSimpleName());
	}

	@Override
	protected PostDTO doRead() throws Exception {
		return iterator.hasNext() ? iterator.next() : null;
	}

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
		path = parameters.getString("path");
	}

	@Override
	protected void doOpen() throws Exception {
		List<PostDTO> listPost = PostReadCSVService.read("src/main/resources/static" + path);
		stream = listPost.stream();
		iterator = stream.iterator();
	}

	@Override
	protected void doClose() throws Exception {
		stream.close();
	}

}
