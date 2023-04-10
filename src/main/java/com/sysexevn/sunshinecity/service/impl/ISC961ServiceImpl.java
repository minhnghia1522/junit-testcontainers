package com.sysexevn.sunshinecity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.converter.SC961Converter;
import com.sysexevn.sunshinecity.dao.ISC961Dao;
import com.sysexevn.sunshinecity.dto.SC961Dto;
import com.sysexevn.sunshinecity.dto.SC961Filter;
import com.sysexevn.sunshinecity.entity.SC961;
import com.sysexevn.sunshinecity.service.ISC961Service;

@Service
public class ISC961ServiceImpl implements ISC961Service {

	
	@Autowired
	private ISC961Dao isc961Dao;
	
	@Autowired
	private SC961Converter converter;
	
	@Override
	public List<SC961Dto> search(SC961Filter filter) {
		List<SC961> list = isc961Dao.findAll();
		return converter.convert(list);
	}

}
