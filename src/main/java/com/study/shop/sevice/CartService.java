package com.study.shop.sevice;

import com.study.shop.domain.cart.CartItem;
import com.study.shop.domain.cart.CartItemRepository;
import com.study.shop.domain.cart.CartRepository;
import com.study.shop.domain.cart.Cart;
import com.study.shop.domain.item.Item;
import com.study.shop.domain.item.ItemRepository;
import com.study.shop.domain.user.User;
import com.study.shop.web.dto.CartDTO;
import com.study.shop.web.dto.CartItemDTO;
import com.study.shop.web.dto.ItemDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    public void save(CartDTO cartDTO) {
        Cart cart = Cart.toCart(cartDTO);
        cartRepository.save(cart);
    }

    public void addCart(User user, Item newItem, int amount) {
        // 유저 id로 해당 유저의 장바구니 찾기
        Cart cart = cartRepository.findByUserId(user.getId());

        // 장바구니가 존재하지 않는다면
        if (cart == null) {
            cart = Cart.createCart(user);

            cartRepository.save(cart);
        }

        Item item = itemRepository.findItemById(newItem.getId());

        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, item, amount);
            cartItemRepository.save(cartItem);
        }

        // 상품이 장바구니에 이미 존재한다면 수량만 증가
        else {
            CartItem update = cartItem;
            update.setCart(cartItem.getCart());
            update.setItem(cartItem.getItem());
            update.addCount(amount);
            update.setCount(update.getCount());
            cartItemRepository.save(update);
        }

        // 카트 상품 총 개수 증가
        cart.setCount(cart.getCount()+amount);
    }

    public List<CartItemDTO> findAllByCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());

//        user(userid, ~~)
//        cart(userid, cartid, count)
//        cartitem(cartid, userid, itemid, count)

        List<CartItem> cartItemList = cartItemRepository.findAllByCartId(cart.getId());
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for (CartItem cartItem: cartItemList) {
            cartItemDTOList.add(CartItemDTO.toCartItemDTO(cartItem));
        }

        return cartItemDTOList;
    }
}
