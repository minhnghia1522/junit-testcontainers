package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

	private Integer id;
	private String title;
	private String postName;
	private String postDescription;
	private Date createdAt;
	private Date updatedAt;
}
