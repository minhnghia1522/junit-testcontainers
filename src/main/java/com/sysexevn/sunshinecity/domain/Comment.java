package com.sysexevn.sunshinecity.domain;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table
public class Comment {

	@Id
	private Integer id;
	@Column(name = "comment_container")
	private String commentContainer;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
}
