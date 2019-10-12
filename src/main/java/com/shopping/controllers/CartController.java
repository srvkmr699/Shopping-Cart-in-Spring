package com.shopping.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.entities.Cart;
import com.shopping.service.CartService;


/**
 * 
 * @author akshay
 * @since 07-10-2019
 */

@Controller("cart")
public class CartController {

	@Autowired
	private CartService cartService;


	@GetMapping("/add_to_cart")
	public String addToCart(@RequestParam Long id,HttpSession session) {
		String email = String.valueOf(session.getAttribute("email"));
		Cart cart = cartService.addToCart(id, email);
		if (cart == null) {
			return "redirect:/view_products";
		}
		return "redirect:/view_products";
	}

	@GetMapping("/go_to_cart")
	public ModelAndView viewCart() {
		List<Cart> carts = cartService.viewCartDetails();
		ModelAndView modelAndView = new ModelAndView();
		if (carts == null) {
			modelAndView.addObject("message", "No products available");
			modelAndView.setViewName("cart_details");
			return modelAndView;
		}
		modelAndView.addObject("carts", carts);
		modelAndView.setViewName("cart_details");
		return modelAndView;
	}

	@GetMapping("/remove_cart_item")
	public String removeCartItem(@RequestParam long id) {
		cartService.deleteCartItem(id);
		return "redirect:/go_to_cart";
	}
}
