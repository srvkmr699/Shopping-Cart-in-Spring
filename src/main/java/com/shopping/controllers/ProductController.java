package com.shopping.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.entities.Product;
import com.shopping.models.ProductDto;
import com.shopping.service.IProductService;

@Controller("product")
public class ProductController {
		
	@Autowired
	IProductService productService;
	
	@GetMapping("/add_product")
	public String addProductView(ModelMap modelMap) {
		modelMap.addAttribute("product", new ProductDto());
		return "addProduct";
	}
	
	@PostMapping("/add_product")
	public ModelAndView addProduct(@ModelAttribute("product") @Valid ProductDto productDto,BindingResult bindingResult) {
			
		ModelAndView modelViewMap = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelViewMap.setViewName("addProduct");
			return modelViewMap;
		}
		Product product = productService.addProduct(productDto);
		if (product == null) {
			modelViewMap.setViewName("redirect:/add_product");
			return modelViewMap;
		}

		modelViewMap.setViewName("addProduct");
		modelViewMap.addObject("message", "Product added successfully");
		return modelViewMap;
	}
	
	
}
