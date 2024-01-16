package com.study.shop.web.dto;

import com.study.shop.domain.cart.Cart;
import com.study.shop.domain.cart.CartItem;
import com.study.shop.domain.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDTO {
    private Long id;
    private User user;
    private Integer count;
    private List<CartItem> cartItems;

    public static CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUser(cart.getUser());
        cartDTO.setCount(cart.getCount());
        cartDTO.setCartItems(cart.getCartItems());
        return cartDTO;
    }
}