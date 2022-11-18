package com.sysexevn.sunshinecity.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		EmployeeDto employee = employeeService.getByUsername(username);
		List<SimpleGrantedAuthority> authorities = employeeService.authorities(employee);
		CustomUserDetails user = CustomUserDetails.builder().username(employee.getUsername()).id(employee.getId())
				.password(employee.getPassword()).authorities(authorities).build();
		return new CustomUserDetails(user);
	}
}
