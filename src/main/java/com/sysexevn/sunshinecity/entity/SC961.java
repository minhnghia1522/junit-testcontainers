package com.sysexevn.sunshinecity.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "retirement")
	private boolean retirement;
	
	@Column(name = "affiliationcompany")
	private String affiliationCompany;
	
	@Column(name = "employeenumber")
	private String employeeNumber;
	
	@Column(name = "userCode")
	private String userCode;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "usernamekana")
	private String userNameKana;
	
	@Column(name = "emailaddress")
	private String emailAddress;
	
	@Column(name = "lastpasswordchangetime")
	private Date lastPasswordChangeTime;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "update_at")
	private Date updateAt;
	
}
