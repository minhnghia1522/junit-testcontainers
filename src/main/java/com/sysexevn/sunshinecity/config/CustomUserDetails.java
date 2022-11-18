package com.sysexevn.sunshinecity.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sysexevn.sunshinecity.dto.EmployeeDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 6061471039161264021L;

	private int id;
	
	@JsonIgnore
	private String username;
	
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(int id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public CustomUserDetails(UserDetails user) {
		this.password = user.getPassword();
		this.username = user.getUsername();
		this.authorities = user.getAuthorities();
	}

	public static CustomUserDetails create(EmployeeDto employeeDto) {
		List<GrantedAuthority> authorities = employeeDto.getEmployeeRole().stream()
				.map(x -> new SimpleGrantedAuthority(x.getRole())).collect(Collectors.toList());
		return new CustomUserDetails(employeeDto.getId(), employeeDto.getFullName(), employeeDto.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
