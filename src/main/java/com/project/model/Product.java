package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "id", nullable = false, columnDefinition = "long")
	/*
	 * @GeneratedValue(generator = GenerationType.IDENTITY) Enter the id value when
	 * adding a product, no need @GeneratedValue annotion in this scenario
	 */
	private Long id;

	@Column(name = "category", nullable = false, length = 64)
	private String category;

	@Column(name = "categorytype", nullable = false, length = 64)
	private String categoryType;

	@Column(name = "name", nullable = false, length = 128)
	private String name;

	@Column(name = "status", nullable = false, length = 1)
	private String status;

	@Column(name = "count", nullable = false, columnDefinition = "long")
	private Long count;

	@Column(name = "price", nullable = false, columnDefinition = "float")
	private Float price;

}
