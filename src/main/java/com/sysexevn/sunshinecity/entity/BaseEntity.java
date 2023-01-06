package com.sysexevn.sunshinecity.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
	@Version
	@Column(name = "version_no")
	public Long version;
}
