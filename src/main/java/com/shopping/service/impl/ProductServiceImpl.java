package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entities.Product;
import com.shopping.entities.ProductCategory;
import com.shopping.models.ProductDto;
import com.shopping.repositories.ProductRepository;
import com.shopping.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(Double.parseDouble(productDto.getPrice()));
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		for(ProductCategory c : ProductCategory.values()) {
			if(productDto.getCategory().equalsIgnoreCase(c.toString())) {
				product.setCategory(productDto.getCategory());
				break;
			}
		}
		return productRepository.save(product);
	}

}
