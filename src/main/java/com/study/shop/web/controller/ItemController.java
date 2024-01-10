package com.study.shop.web.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.study.shop.sevice.ItemService;
import com.study.shop.web.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        itemService.updateViewCount(id);
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item", itemDTO);
        return "itemDetail";
    }

    @GetMapping("/item/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/item/";
    }

    // /item/paging?page=1
    @GetMapping("/item/paging")
    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model) {
        pageable.getPageNumber();
        Page<ItemDTO> itemList = itemService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < itemList.getTotalPages()) ? startPage + blockLimit - 1 : itemList.getTotalPages();    //마지막페이지

        model.addAttribute("itemList", itemList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }
}
