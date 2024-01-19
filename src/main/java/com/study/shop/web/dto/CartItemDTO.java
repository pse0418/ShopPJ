package com.study.shop.web.dto;

import com.study.shop.domain.cart.Cart;
import com.study.shop.domain.cart.CartItem;
import com.study.shop.domain.item.Item;
import com.study.shop.domain.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemDTO {
    private Long id;
    private Cart cart;
    private Item item;
    private Integer count;

    public static CartItemDTO toCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCart(cartItem.getCart());
        cartItemDTO.setItem(cartItem.getItem());
        cartItemDTO.setCount(cartItem.getCount());
        return cartItemDTO;
    }
}
