package com.shopping.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7188558255571237967L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@Column(name = "name")
	String name;
	@Column(name = "price")
    Double price;
	@Column(name = "description")
    String description;
	@Column(name = "image_name")
	String imageUrl;
	@Column(name = "category")
	String category;

	public Product(String name, Double price, String description, String imageUrl, String category) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
		this.category = category;
	}
}
