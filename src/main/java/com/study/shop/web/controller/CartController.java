package com.study.shop.web.controller;

import com.study.shop.domain.item.Item;
import com.study.shop.domain.user.User;
import com.study.shop.sevice.CartService;
import com.study.shop.sevice.ItemService;
import com.study.shop.sevice.UserService;
import com.study.shop.web.dto.CartDTO;
import com.study.shop.web.dto.ItemDTO;
import com.study.shop.web.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
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
    private final UserService userService;
    private final ItemService itemService;

//    @GetMapping("/cart/cart/{id}/{itemId}")
//    public String cartItem(Model model) {
//        List<CartDTO> itemDTOList = cartService.findAll();
//        model.addAttribute("itemList", itemDTOList);
//        return "itemList";
//    }

    @GetMapping("/cart")
    public String findByUserId(Model model, HttpSession session) {
        Long id = (Long)session.getAttribute("loginId");
        List<CartDTO> cartDTOList = cartService.findByUserId(id);
        model.addAttribute("cartList", cartDTOList);
        return "cartList";
    }

//    @PostMapping("/user/cart/{id}/{itemId}")
//    public String cartItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, int amount, HttpSession session) {
    @PostMapping("/item/{itemId}")
    public String cartItem(@PathVariable("itemId") Long itemId, HttpSession session) {
        Long id = (Long)session.getAttribute("loginId");
        int amount = 1;

        User user = User.toUpdateUser(userService.findById(id));
        Item item = Item.toItem(itemService.findById(itemId));


        System.out.println(user.getId());
        cartService.addCart(user, item, amount);

        return "redirect:/item/";
    }
}
