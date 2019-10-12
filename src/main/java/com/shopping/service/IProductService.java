package com.shopping.service;

import java.util.List;


import com.shopping.entities.Product;
import com.shopping.models.ProductDto;

public interface IProductService {

	Product addProduct(ProductDto productDto);
	List<Product> getAllProducts();
	Product findProductById(long id);
	Product updateProduct(ProductDto productDto);
	void deleteProductById(long id);
}
