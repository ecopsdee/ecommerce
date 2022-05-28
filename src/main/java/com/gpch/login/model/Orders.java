package com.gpch.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderID")
    private int orderId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "product_ID", referencedColumnName = "product_id")
    private Product product;

    @NotNull
    @Min(value = 0)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_Id", referencedColumnName = "cartId")
    private Cart cart;
}
