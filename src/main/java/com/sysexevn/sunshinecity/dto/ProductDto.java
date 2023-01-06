package com.sysexevn.sunshinecity.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
	private Integer id;
	private String title;
	private String picture;
	private String description;
	private BigDecimal oldPrice;
	private BigDecimal newPrice;
	private String shopName;
	private Long version;
}
