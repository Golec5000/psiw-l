package com.pwr.psiw.orderservice.utils;

import com.pwr.psiw.orderservice.model.Product;
import com.pwr.psiw.orderservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BdInit {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product(null, "Laptop", BigDecimal.valueOf(3999.99)),
                    new Product(null, "Smartphone", BigDecimal.valueOf(2499.50)),
                    new Product(null, "Tablet", BigDecimal.valueOf(1599.99)),
                    new Product(null, "Smartwatch", BigDecimal.valueOf(999.99)),
                    new Product(null, "Wireless Headphones", BigDecimal.valueOf(799.99)),
                    new Product(null, "Gaming Mouse", BigDecimal.valueOf(299.99)),
                    new Product(null, "Mechanical Keyboard", BigDecimal.valueOf(499.99)),
                    new Product(null, "Monitor 27''", BigDecimal.valueOf(1299.99)),
                    new Product(null, "External SSD 1TB", BigDecimal.valueOf(699.99)),
                    new Product(null, "USB-C Hub", BigDecimal.valueOf(199.99))
            );

            productRepository.saveAll(products);
            System.out.println("✅ Added 10 test products to the database.");
        } else {
            System.out.println("ℹ️ Database already contains products. Skipping initialization.");
        }
    }
}
