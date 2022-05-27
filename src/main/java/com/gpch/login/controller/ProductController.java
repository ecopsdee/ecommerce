package com.gpch.login.controller;

import com.gpch.login.model.Product;
import com.gpch.login.model.User;
import com.gpch.login.service.FactoryInterface.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/productupload")
    public ModelAndView createProduct(){
        ModelAndView modelAndView = new ModelAndView();
        Product product = new Product();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("productUpload");
        return modelAndView;
    }

    @PostMapping(value = "/productupload")
    public ModelAndView createProduct(@Valid Product product, BindingResult bindingResult) {
        log.info("Inside the Create Product Service");
        log.info("Product Name = " + product.getName());
        ModelAndView modelAndView = new ModelAndView();
        if (productService.findProductByName(product.getName()).isPresent()) {
            bindingResult
                    .rejectValue("name", "error.name",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("productUpload");
        } else {
            productService.saveProduct(product);
            modelAndView.addObject("successMessage", "Product has been registered successfully");
            modelAndView.addObject("product", new Product());
            modelAndView.setViewName("productUpload");

        }
        return modelAndView;
    }

}
