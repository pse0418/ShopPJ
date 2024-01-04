package com.study.shop.web.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.study.shop.sevice.ItemService;
import com.study.shop.web.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/item/save")
    public String saveForm() {
        return "saveItem";
    }

    @PostMapping("/item/save")
    public String save(@ModelAttribute ItemDTO itemDTO) {
        System.out.println("ItemController.save");  //soutm
        System.out.println("itemDTO = " + itemDTO);
        itemService.save(itemDTO);
        return "main";
    }

    @GetMapping("/item/")
    public String findAll(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAll();
        model.addAttribute("itemList", itemDTOList);
        return "itemList";
    }

    @GetMapping("/item/{id}")
    public String findById(@PathVariable Long id, Model model) {
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item", itemDTO);
        return "itemDetail";
    }

    @GetMapping("/item/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/item/";
    }
}
