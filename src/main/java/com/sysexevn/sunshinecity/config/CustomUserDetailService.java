package com.sysexevn.sunshinecity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sysexevn.sunshinecity.dto.EmployeeDto;
import com.sysexevn.sunshinecity.service.IEmployeeService;

@Configuration
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	public IEmployeeService employeeService;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeDto employee = employeeService.getByEmail(username);
		CustomUserDetails user = CustomUserDetails.builder().username(employee.getEmail()).id(employee.getEmployeeId())
				.password(employee.getPassWord())
				.authorities(
						employee.getEmployeeRole().stream().map(x -> new SimpleGrantedAuthority(x.getRole())).toList())
				.build();
		return new CustomUserDetails(user);
	}
}
