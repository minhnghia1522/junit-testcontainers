package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

	
	private Integer id;
	
	@NotBlank(message = "title is not blank!")
	@NotNull(message = "title is not null!")
	@Size(min = 3, max = 50, message = "title length is not allowed")
	private String title;
	private String postName;
	private String postDescription;
	private Date createdAt;
	private Date updatedAt;
	private Integer postTypeId;
	private Integer acceptedAnswerId;
	private Integer parentId;
	private Integer score;
	private Integer viewCount;
	private String body;
	private Integer ownerUserId;
	private String ownerDisplayName;
	private String tags;
	private Integer answerCount;
	private Integer commentCount;
	private Integer favoriteCount;
	private Date closedDate;
	private Date communityOwnerDate;
}
