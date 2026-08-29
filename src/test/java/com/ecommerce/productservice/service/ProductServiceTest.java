package com.ecommerce.productservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProductServiceTest {

  private ProductRepository repository;
  private ProductService service;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(ProductRepository.class);
    service = new ProductService(repository);
  }

  @Test
  void getById_returnsProductWhenPresent() {
    Product product = new Product("Desk", "A desk", 1000, "SKU-1");
    Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));

    assertThat(service.getById(1L).getName()).isEqualTo("Desk");
  }

  @Test
  void getById_throwsWhenMissing() {
    Mockito.when(repository.findById(7L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.getById(7L)).isInstanceOf(ProductNotFoundException.class);
  }
}
