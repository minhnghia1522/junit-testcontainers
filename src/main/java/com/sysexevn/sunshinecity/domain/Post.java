package com.sysexevn.sunshinecity.domain;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import lombok.Data;

@Entity
@Data
public class Post {

	@Id
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

	public Post(Integer id, String title, String postName, String postDescription, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.postName = postName;
		this.postDescription = postDescription;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Post() {
		super();
	}
}
