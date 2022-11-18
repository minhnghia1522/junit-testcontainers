package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import com.poiji.annotation.ExcelCellName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTOExcel {

	@ExcelCellName("id")
	private Integer id;

	@ExcelCellName("title")
	private String title;

	@ExcelCellName("post_name")
	private String postName;

	@ExcelCellName("post_description")
	private String postDescription;

	@ExcelCellName("created_at")
	private Date createdAt;

	@ExcelCellName("updated_at")
	private Date updatedAt;

	@ExcelCellName("post_type_id")
	private Integer postTypeId;

	@ExcelCellName("accepted_answer_id")
	private Integer acceptedAnswerId;

	@ExcelCellName("parent_id")
	private Integer parentId;

	@ExcelCellName("score")
	private Integer score;

	@ExcelCellName("view_count")
	private Integer viewCount;

	@ExcelCellName("body")
	private String body;

	@ExcelCellName("owner_user_id")
	private Integer ownerUserId;

	@ExcelCellName("owner_display_name")
	private String ownerDisplayName;

	@ExcelCellName("tags")
	private String tags;

	@ExcelCellName("answer_count")
	private Integer answerCount;

	@ExcelCellName("comment_count")
	private Integer commentCount;

	@ExcelCellName("favorite_count")
	private Integer favoriteCount;

	@ExcelCellName("closed_date")
	private Date closedDate;

	@ExcelCellName("community_owner_date")
	private Date communityOwnerDate;
}
