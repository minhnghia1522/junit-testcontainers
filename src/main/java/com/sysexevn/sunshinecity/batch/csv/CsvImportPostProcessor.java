package com.sysexevn.sunshinecity.batch.csv;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.converter.PostConverter;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.Post;

@Component
public class CsvImportPostProcessor implements ItemProcessor<PostDTO, Post> {

	@Autowired
	private PostConverter converter;
	
	@Override
	public Post process(PostDTO item) throws Exception {
		
		if(item.getScore() != null && item.getViewCount() != null)
			item.setScore(calculatorScoreView(item.getScore(), item.getViewCount()));
		if(item.getCommunityOwnerDate() != null)
			item.setCommunityOwnerDate(plusDate(item.getCommunityOwnerDate()));
		
		return converter.convertToDomain(item);
	}
	
	private int calculatorScoreView(int score, int viewCount) {
		return score + viewCount;
	}
	
	private Date plusDate(Date date) {
		int days = 1;
		return new Date(date.getTime() + ((1000*60*60*24) * days));
	}

}
