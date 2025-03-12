package com.pwr.psiw.orderservice.repository;

import com.pwr.psiw.orderservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
