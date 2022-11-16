package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import com.sysexevn.sunshinecity.annotation.SheetName;
import com.sysexevn.sunshinecity.annotation.XlsxColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SheetName(value = "Sheet1")
public class PostDTO {

	@XlsxColumn(colName = "id")
	private Integer id;
	
	@XlsxColumn(colName = "title")
	private String title;
	
	@XlsxColumn(colName = "post_name")
	private String postName;
	
	@XlsxColumn(colName = "post_description")
	private String postDescription;
	
	@XlsxColumn(colName = "created_at")
	private Date createdAt;
	
	@XlsxColumn(colName = "updated_at")
	private Date updatedAt;
	
	@XlsxColumn(colName = "post_type_id")
	private Integer postTypeId;
	
	@XlsxColumn(colName = "accepted_answer_id")
	private Integer acceptedAnswerId;
	
	@XlsxColumn(colName = "parent_id")
	private Integer parentId;
	
	@XlsxColumn(colName = "score")
	private Integer score;
	
	@XlsxColumn(colName = "view_count")
	private Integer viewCount;
	
	@XlsxColumn(colName = "body")
	private String body;
	
	@XlsxColumn(colName = "owner_user_id")
	private Integer ownerUserId;
	
	@XlsxColumn(colName = "owner_display_name")
	private String ownerDisplayName;
	
	@XlsxColumn(colName = "tags")
	private String tags;
	
	@XlsxColumn(colName = "answer_count")
	private Integer answerCount;
	
	@XlsxColumn(colName = "comment_count")
	private Integer commentCount;
	
	@XlsxColumn(colName = "comment_count")
	private Integer favoriteCount;
	
	@XlsxColumn(colName = "closed_date")
	private Date closedDate;
	
	@XlsxColumn(colName = "community_owner_date")
	private Date communityOwnerDate;
}
