package com.ecommerce.productservice.web;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.service.ProductNotFoundException;
import com.ecommerce.productservice.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired private MockMvc mvc;

  @MockitoBean private ProductService service;

  @Test
  void list_returnsProducts() throws Exception {
    when(service.findAll()).thenReturn(List.of(new Product("Desk", "A desk", 1000, "SKU-1")));

    mvc.perform(get("/products"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Desk"))
        .andExpect(jsonPath("$[0].priceCents").value(1000));
  }

  @Test
  void getById_returnsProduct() throws Exception {
    when(service.getById(1L)).thenReturn(new Product("Desk", "A desk", 1000, "SKU-1"));

    mvc.perform(get("/products/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sku").value("SKU-1"));
  }

  @Test
  void getById_missing_returns404ProblemBody() throws Exception {
    when(service.getById(eq(99L))).thenThrow(new ProductNotFoundException(99L));

    mvc.perform(get("/products/99"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value("PRODUCT_NOT_FOUND"))
        .andExpect(jsonPath("$.status").value(404));
  }
}
