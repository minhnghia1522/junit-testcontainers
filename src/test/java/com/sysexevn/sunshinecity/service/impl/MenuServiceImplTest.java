package com.sysexevn.sunshinecity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sysexevn.sunshinecity.dao.IMenuDao;

@SpringBootTest
public class MenuServiceImplTest {
	@Autowired
	IMenuDao menuDao;

	@Test
	void contextLoads() {
	}
}
