package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/** Data access for {@link Product}. */
public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsBySku(String sku);
}
