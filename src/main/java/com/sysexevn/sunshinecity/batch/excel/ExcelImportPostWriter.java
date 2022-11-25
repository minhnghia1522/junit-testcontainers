package com.sysexevn.sunshinecity.batch.excel;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysexevn.sunshinecity.dao.IPostDAO;
import com.sysexevn.sunshinecity.entity.Post;

@Component
public class ExcelImportPostWriter implements ItemWriter<Post> {

	@Autowired
	private IPostDAO postDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends Post> items) throws Exception {
		postDao.insertAllUseDSL((List<Post>) items);
	}

}
