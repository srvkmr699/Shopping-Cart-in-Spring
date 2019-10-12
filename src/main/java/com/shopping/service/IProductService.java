package com.shopping.service;

import com.shopping.entities.Product;
import com.shopping.models.ProductDto;

public interface IProductService {

	Product addProduct(ProductDto productDto);
}
