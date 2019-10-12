package com.shopping.models;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductDto {
	
	
	Long id;
	@NotBlank(message = "name must not be blank")
	String name;
	@NotBlank(message = "price must not be blank")
	String price;
	@NotBlank(message = "Description field must not be blank")
	String description;
	@NotBlank(message = "image field must not be blank")
	String imageUrl;
	@NotBlank(message = "category must not be blank")
	String category;

}
