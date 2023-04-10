package com.sysexevn.sunshinecity.service;

import java.util.List;

import com.sysexevn.sunshinecity.dto.SC961Dto;
import com.sysexevn.sunshinecity.dto.SC961Filter;

public interface ISC961Service {

	List<SC961Dto> search(SC961Filter filter);
	
	SC961Dto findById(Long id);
	
	void create(SC961Dto dto);
	
	void update(SC961Dto dto);
	
}
