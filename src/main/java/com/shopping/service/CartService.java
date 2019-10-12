package com.shopping.service;

import java.util.List;

import com.shopping.entities.Cart;
import com.shopping.entities.Product;
import com.shopping.entities.User;

/**
 * 
 * @author akshay
 * @since 07-10-2019
 *
 */
public interface CartService {
	
	/**
	 * Adding product to cart
	 * 
	 * @param product
	 * @return Cart
	 */
	Cart addToCart(Long productId ,String email);
	
	/**
	 * View list of products in the cart
	 * @return List of Cart items
	 */
	List<Cart> viewCartDetails();
	
	/**
	 * Remove items from cart
	 * @param id
	 */
	void deleteCartItem(long id);
}
