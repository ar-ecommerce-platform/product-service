package com.ecommerce.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** A sellable product. Prices are stored in integer cents to avoid floating-point error. */
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 1000)
  private String description;

  @Column(nullable = false)
  private long priceCents;

  @Column(nullable = false, unique = true)
  private String sku;

  protected Product() {
    // for JPA
  }

  public Product(String name, String description, long priceCents, String sku) {
    this.name = name;
    this.description = description;
    this.priceCents = priceCents;
    this.sku = sku;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public long getPriceCents() {
    return priceCents;
  }

  public String getSku() {
    return sku;
  }
}
