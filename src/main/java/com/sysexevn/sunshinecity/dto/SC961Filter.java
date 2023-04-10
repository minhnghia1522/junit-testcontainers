package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SC961Filter {

	private Integer id;

	private boolean retirement;

	private String affiliationCompany;

	private String employeeNumber;

	private String userCode;

	private String userName;

	private String userNameKana;

	private String emailAddress;

	private Date lastPasswordChangeTime;

}
