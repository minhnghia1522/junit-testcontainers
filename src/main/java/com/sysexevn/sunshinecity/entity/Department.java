package com.sysexevn.sunshinecity.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@SequenceGenerator(sequence = "department_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "sc961_id")
	private Long sc961Id ;

	@Column(name = "roll")
	private String roll;
	
	@Column(name = "dept")
	private String dept;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "line_g")
	private String lineG;
	
	@Column(name = "priority")
	private String priority;
	
	@Column(name = "valid_start_date")
	private Date validStartDate;
	
	@Column(name = "valid_end_date")
	private Date validEndDate;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "update_at")
	private Date updateAt;
}
