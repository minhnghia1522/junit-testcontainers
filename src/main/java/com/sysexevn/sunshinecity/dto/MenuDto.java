package com.sysexevn.sunshinecity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MenuDto {

	public Integer id;

	public String menuName;

	public String menuPath;

	public Date createdAt;

	public Date updateAt;

}
