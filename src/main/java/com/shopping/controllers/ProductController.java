package com.shopping.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.entities.Product;
import com.shopping.models.ProductDto;
import com.shopping.service.IProductService;

@Controller("product")
public class ProductController {
		
	@Autowired
	private IProductService productService;
	
	@GetMapping("/add_product")
	public String addProductView(ModelMap modelMap) {
		modelMap.addAttribute("product", new ProductDto());
		return "add_product";
	}
	
	@PostMapping("/add_product")
	public ModelAndView addProduct(@ModelAttribute("product") @Valid ProductDto productDto,BindingResult bindingResult) {
			
		ModelAndView modelViewMap = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelViewMap.setViewName("add_product");
			return modelViewMap;
		}
		Product product = productService.addProduct(productDto);
		if (product == null) {
			modelViewMap.setViewName("redirect:/add_product");
			return modelViewMap;
		}

		modelViewMap.setViewName("add_product");
		modelViewMap.addObject("message", "Product added successfully");
		return modelViewMap;
	}
	
	@GetMapping("/view_products")
	public ModelAndView viewProducts() {
		List<Product> products = productService.getAllProducts();
		System.out.println(products);
		return new ModelAndView("view_products","products",products);
	}
	
	@PostMapping("/update_product")
	public ModelAndView updateProduct(@ModelAttribute("product") @Valid ProductDto productDto,BindingResult bindingResult){
		Product product = productService.updateProduct(productDto);
		ModelAndView modelAndView = new ModelAndView();
		if(product==null) {
			modelAndView.setViewName("update_product");
			modelAndView.addObject("message","product not updated");
			return modelAndView;
		}
		modelAndView.setViewName("update_product");
		modelAndView.addObject("message","product  updated successfully");
		return modelAndView;
	}
	
	@GetMapping("/update_product")
	public ModelAndView viewProductById(@RequestParam("id") long id) {
		Product product = productService.findProductById(id);
		return new ModelAndView("update_product","product",product);
	}
	
	@GetMapping("/delete_product")
	public String deleteProduct(@RequestParam("id") long id) {
		productService.deleteProductById(id);
		return "redirect:/view_products";
	}
	
}
