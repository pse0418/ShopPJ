package com.study.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShopController {

    @GetMapping("/") //localhost:8080/shop/login
    public String index() {
        return "index";
    }
}
