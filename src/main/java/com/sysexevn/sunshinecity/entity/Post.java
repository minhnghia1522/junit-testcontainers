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

@Entity(immutable = true, metamodel = @Metamodel)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String title;

	@Column(name = "post_name")
	private String postName;

	@Column(name = "post_description")
	private String postDescription;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "post_type_id")
	private Integer postTypeId;
	
	@Column(name = "accepted_answer_id")
	private Integer acceptedAnswerId;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "view_count")
	private Integer viewCount;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "owner_user_id")
	private Integer ownerUserId;
	
	@Column(name = "owner_display_name")
	private String ownerDisplayName;
	
	@Column(name = "tags")
	private String tags;
	
	@Column(name = "answer_count")
	private Integer answerCount;
	
	@Column(name = "comment_count")
	private Integer commentCount;
	
	@Column(name = "favorite_count")
	private Integer favoriteCount;
	
	@Column(name = "closed_date")
	private Date closedDate;
	
	@Column(name = "community_owner_date")
	private Date communityOwnerDate;
	
}
