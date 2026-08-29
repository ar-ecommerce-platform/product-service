package com.ecommerce.productservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.productservice.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProductRepositoryTest {

  @Autowired private ProductRepository repository;

  @Test
  void existsBySku_reflectsPersistence() {
    repository.save(new Product("Desk", "A desk", 1000, "SKU-DESK-1"));

    assertThat(repository.existsBySku("SKU-DESK-1")).isTrue();
    assertThat(repository.existsBySku("SKU-UNKNOWN")).isFalse();
  }
}
