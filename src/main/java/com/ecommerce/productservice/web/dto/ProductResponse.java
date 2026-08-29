package com.ecommerce.productservice.web.dto;

import com.ecommerce.productservice.entity.Product;

/** Public view of a product. */
public record ProductResponse(
    Long id, String name, String description, long priceCents, String sku) {

  public static ProductResponse from(Product product) {
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPriceCents(),
        product.getSku());
  }
}
