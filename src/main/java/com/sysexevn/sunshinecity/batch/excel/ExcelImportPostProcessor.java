package com.sysexevn.sunshinecity.batch.excel;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.converter.PostConverter;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.Post;

@Component
public class ExcelImportPostProcessor implements ItemProcessor<PostDTO, Post> {

	@Autowired
	private PostConverter converter;
	
	@Override
	public Post process(PostDTO item) throws Exception {
		return converter.convertToDomain(item);
	}
	
	// calculator logic ...
	
}
