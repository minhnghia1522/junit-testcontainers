package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentDto {

	private Integer id;
	
	private Integer sc961Id;
	
	private String roll;
	
	private String dept;

	private String company;
	
	private String lineG;

	private String priority;
	
	private Date validStartDate;

	private Date validEndDate;
	
	private Date createdAt;
	
	private Date updateAt;
}
