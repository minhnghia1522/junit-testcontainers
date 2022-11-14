package com.sysexevn.sunshinecity.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

@Configuration
public class UserDetailServiceConfig implements UserDetailsService {

	@Autowired
	public IEmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeDto employee = employeeService.getByEmail(username);
		UserDetails user = User.withUsername(employee.getEmail()).password(passwordEncoder().encode(employee.getPassWord()))
				.roles(employee.getEmployeeRole().stream().map(x -> x.getRole()).toArray(String[]::new))
				.build();
		return user;
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
