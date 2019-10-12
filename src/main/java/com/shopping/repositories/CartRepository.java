package com.shopping.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entities.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUserId(long id);
	
	
}
