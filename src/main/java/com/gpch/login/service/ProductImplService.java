package com.gpch.login.service;

import com.gpch.login.model.Product;
import com.gpch.login.repository.ProductRepository;
import com.gpch.login.service.FactoryInterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImplService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProduct(Product product) {
        Optional<Product> _product = productRepository.findById(product.getId());
        return _product.get();
    }

    @Override
    public Iterable<Product> findAllProduct(Product product) {
        return productRepository.findAll();
    }

    @Override
    public Product editProduct(Product product) {
        Optional<Product> _product = productRepository.findById(product.getId());
        _product.get().setName(product.getName());
        _product.get().setPrice(product.getPrice());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }
}
