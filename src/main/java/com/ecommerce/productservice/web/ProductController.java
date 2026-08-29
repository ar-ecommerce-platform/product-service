package com.ecommerce.productservice.web;

import com.ecommerce.productservice.service.ProductService;
import com.ecommerce.productservice.web.dto.ProductResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Read-only catalog endpoints. */
@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public List<ProductResponse> list() {
    return service.findAll().stream().map(ProductResponse::from).toList();
  }

  @GetMapping("/{id}")
  public ProductResponse getById(@PathVariable Long id) {
    return ProductResponse.from(service.getById(id));
  }
}
