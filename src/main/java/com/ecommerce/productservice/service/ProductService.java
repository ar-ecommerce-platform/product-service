package com.ecommerce.productservice.service;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Read access to the product catalog. */
@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public List<Product> findAll() {
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  public Product getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }
}
