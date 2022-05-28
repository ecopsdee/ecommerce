package com.gpch.login.service.FactoryInterface;

import com.gpch.login.model.Product;

import java.util.Optional;

public interface ProductService {
    public Product saveProduct(Product product);
    public Product findProduct(Product product);
    public Product findProduct(Integer product_id);
    public Iterable<Product> findAllProduct();
    public Product editProduct(Product product);
    public void deleteProduct(Product product);

    Optional<Product> findProductByName(String name);


}
