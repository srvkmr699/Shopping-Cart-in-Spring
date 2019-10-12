package com.shopping.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cart_details")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Cart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JoinTable(name = "cart_product", joinColumns = {
			@JoinColumn(name = "cart_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "product_id", referencedColumnName = "id") })
	List<Product> products;

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinTable(name = "cart_user", joinColumns = {
			@JoinColumn(name = "cart_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	User user;

	@Column(name = "total_amount")
	Double totalAmount;
}
