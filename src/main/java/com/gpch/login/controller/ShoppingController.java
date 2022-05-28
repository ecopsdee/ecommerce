package com.gpch.login.controller;

import com.gpch.login.model.Orders;
import com.gpch.login.model.Product;
import com.gpch.login.service.FactoryInterface.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.lang.model.type.IntersectionType;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Controller
@Slf4j
public class ShoppingController {

    @Autowired
    private ProductService productService;

    List<Orders> items_selected_by_user = new LinkedList<>(); //this is the list that holds all the orders selected by the user.

    @GetMapping(value = "/shopping")
    public ModelAndView shopping(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("items_selected_by_user") != null){
            List<Orders> items_selected_by_user = (List<Orders>)session.getAttribute("items_selected_by_user");
 //           log.info(items_selected_by_user.get(0).getProduct().getName());
        }

 //       Iterator<Product> list_items = productService.findAllProduct().iterator();
        modelAndView.addObject("list_items", productService.findAllProduct().iterator());
        modelAndView.addObject("cart", session.getAttribute("items_selected_by_user"));
//        log.info("cart: items_selected_by_user. ");
        modelAndView.setViewName("shopping");
        return modelAndView;
    }

    @PostMapping(value = "/buy")
    public RedirectView buy(@RequestParam("qty") int qty, @RequestParam("item_id")int item_id, HttpSession session){
//        log.info("Save the quantity " + qty +", " + item_id);
        if (session.getAttribute("items_selected_by_user") == null){
            items_selected_by_user.add(selected_order(qty, productService.findProduct(item_id)));
            session.setAttribute("items_selected_by_user",items_selected_by_user);
        }else{
            items_selected_by_user.add(selected_order(qty, productService.findProduct(item_id)));
            session.setAttribute("items_selected_by_user",items_selected_by_user);
        }
        return new RedirectView("/shopping");
    }

    private Orders selected_order(Integer qty, Product product){
        Orders orders = new Orders();
        orders.setProduct(product);
        orders.setQuantity(qty);
        orders.setTotalPrice(product.getPrice().multiply(new BigDecimal(qty)));
        return orders;
    }



}
