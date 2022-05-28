package com.gpch.login.service;

import com.gpch.login.model.Cart;
import com.gpch.login.repository.CartRepository;
import com.gpch.login.service.FactoryInterface.CartInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartImplService implements CartInterface {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
