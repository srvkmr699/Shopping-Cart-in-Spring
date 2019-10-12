package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entities.Cart;
import com.shopping.entities.Product;
import com.shopping.entities.User;
import com.shopping.repositories.CartRepository;
import com.shopping.service.CartService;
import com.shopping.service.IProductService;
import com.shopping.service.IUserService;

/**
 * 
 * @author akshay
 * @since 08-10-2019
 *
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private IProductService productService;

	@Autowired
	private IUserService userService;

	/**
	 * @description Adding product to cart
	 * @param productId
	 * @param email
	 * @return Cart
	 */
	@Override
	public Cart addToCart(Long productId, String email) {
		Product product = productService.findProductById(productId);
		User user = userService.findByEmail(email);
		long userId = user.getId();
		Optional<Cart> optional = cartRepository.findByUserId(userId);
		Cart cart = optional.orElse(new Cart());
		if (cart.getUser() == null) {
			cart.setUser(user);
		}
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		cart.setProducts(products);
		return cartRepository.save(cart);
	}

	/**
	 * @description View list of products in the cart
	 * @return Cart
	 */
	@Override
	public List<Cart> viewCartDetails() {
		return cartRepository.findAll();
	}

	/**
	 * @description delete items from cart
	 * @param cart id
	 * 
	 */
	@Override
	public void deleteCartItem(long id) {
		cartRepository.deleteById(id);
	}

}
