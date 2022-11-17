package com.sysexevn.sunshinecity.domain;

import java.math.BigDecimal;

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
@AllArgsConstructor()
@NoArgsConstructor
@Getter
@Setter
public class Product {
	@Id
	@SequenceGenerator(sequence = "product_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String title;
	private String picture;
	private String description;
	@Column(name = "old_price")
	private BigDecimal oldPrice;
	@Column(name = "new_price")
	private BigDecimal newPrice;
	@Column(name = "shop_name")
	private String shopName;
}
