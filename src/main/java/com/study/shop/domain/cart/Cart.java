package com.study.shop.domain.cart;

import com.study.shop.domain.user.User;
import com.study.shop.web.dto.CartDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Cart<CartItem> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    private Integer count;  // 담긴 총 상품 수

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    public static Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setCount(0);
        cart.setUser(user);
        return cart;
    }

    public static Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUser(cartDTO.getUser());
        cart.setCount(0);
        return cart;
    }
}