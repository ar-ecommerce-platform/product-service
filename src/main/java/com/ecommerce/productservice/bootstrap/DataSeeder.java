package com.ecommerce.productservice.bootstrap;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Seeds a small demo catalog on startup when the table is empty. */
@Component
public class DataSeeder implements CommandLineRunner {

  private final ProductRepository repository;

  public DataSeeder(ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) {
    if (repository.count() > 0) {
      return;
    }
    repository.saveAll(
        List.of(
            new Product("Aeron Chair", "Ergonomic office chair", 129900, "SKU-CHAIR-01"),
            new Product("Standing Desk", "Height-adjustable desk", 89900, "SKU-DESK-01"),
            new Product("Mechanical Keyboard", "Tactile switches, compact", 14900, "SKU-KEEB-01"),
            new Product("4K Monitor", "27-inch UHD display", 39900, "SKU-MON-01"),
            new Product("USB-C Hub", "7-in-1 port expander", 4900, "SKU-HUB-01")));
  }
}
