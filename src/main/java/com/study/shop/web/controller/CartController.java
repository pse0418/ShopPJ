package com.study.shop.web.controller;

import com.study.shop.domain.item.Item;
import com.study.shop.domain.user.User;
import com.study.shop.sevice.CartService;
import com.study.shop.sevice.ItemService;
import com.study.shop.sevice.UserService;
import com.study.shop.web.dto.ItemDTO;
import com.study.shop.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

//    @GetMapping("/cart/cart/{id}/{itemId}")
//    public String cartItem(Model model) {
//        List<CartDTO> itemDTOList = cartService.findAll();
//        model.addAttribute("itemList", itemDTOList);
//        return "itemList";
//    }

    @PostMapping("/cart/cart/{id}/{itemId}")
    public String cartItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, int amount) {

        User user = User.toUser(UserService.findById(id));
        Item item = Item.toItem(ItemService.findById(itemId));

        cartService.addCart(user, item, amount);

        return "redirect:/item/view/{itemId}";
    }


}
