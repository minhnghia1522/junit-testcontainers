package com.sysexevn.sunshinecity.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SC961 {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "retirement")
	private boolean retirement;
	
	@Column(name = "affiliationCompany")
	private String affiliationCompany;
	
	@Column(name = "employeeNumber")
	private String employeeNumber;
	
	@Column(name = "userCode")
	private String userCode;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userNameKana")
	private String userNameKana;
	
	@Column(name = "emailAddress")
	private String emailAddress;
	
	@Column(name = "lastPasswordChangeTime")
	private Date lastPasswordChangeTime;
	
}
