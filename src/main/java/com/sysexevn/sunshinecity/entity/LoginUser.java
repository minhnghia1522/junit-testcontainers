package com.sysexevn.sunshinecity.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(metamodel = @Metamodel, immutable = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

	@Id
	@Column(name = "token")
	private String token;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "pass_word")
	private String passWord;
}
