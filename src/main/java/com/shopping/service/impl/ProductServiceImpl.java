package com.shopping.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entities.Product;
import com.shopping.models.ProductDto;
import com.shopping.repositories.ProductRepository;
import com.shopping.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product addProduct(ProductDto productDto) {
		String productName = productDto.getName();
		Double productPrice = Double.parseDouble(productDto.getPrice());
		String productDescription = productDto.getDescription();
		String imageUrl = productDto.getImageUrl();
		String category = productDto.getCategory();
		Product product = new Product(productName, productPrice, productDescription, imageUrl, category);
		return productRepository.save(product);		
	}


	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}


	@Override
	public Product findProductById(long id) {
		return productRepository.findById(id).get();
	}


	@Override
	public Product updateProduct(ProductDto productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setPrice(Double.parseDouble(productDto.getPrice()));
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setCategory(productDto.getCategory());
		return productRepository.save(product);
	}


	@Override
	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}

}
