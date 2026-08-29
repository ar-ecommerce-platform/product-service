package com.ecommerce.productservice.service;

/** Thrown when a product id does not exist. */
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("No product with id " + id);
  }
}
