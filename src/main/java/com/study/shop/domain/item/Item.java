package com.study.shop.domain.item;

import com.study.shop.web.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "item_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 50) // not null
    private String itemNm;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, name ="number")
    private Integer stockNumber;

    @Column
    private String itemDetail;

    @Column
    private Integer viewCount;


    public static Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemNm(itemDTO.getItemNm());
        item.setPrice(itemDTO.getPrice());
        item.setStockNumber(itemDTO.getStockNumber());
        item.setItemDetail(itemDTO.getItemDetail());
        item.setViewCount(itemDTO.getViewCount());
        return item;
    }

    public static Item toUpdateItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setItemNm(itemDTO.getItemNm());
        item.setPrice(itemDTO.getPrice());
        item.setStockNumber(itemDTO.getStockNumber());
        item.setItemDetail(itemDTO.getItemDetail());
        return item;
    }

}
