package com.study.shop.domain.item;

import com.study.shop.domain.cart.CartItem;
import com.study.shop.domain.user.User;
import com.study.shop.web.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@DynamicInsert
//@DynamicUpdate
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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; // 판매자 아이디

    @OneToMany(mappedBy = "item")
    private List<CartItem> cart_items = new ArrayList<>();

    public static Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setItemNm(itemDTO.getItemNm());
        item.setPrice(itemDTO.getPrice());
        item.setStockNumber(itemDTO.getStockNumber());
        item.setItemDetail(itemDTO.getItemDetail());
        item.setViewCount(0);
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
