package com.study.shop.web.dto;

import com.study.shop.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemDTO {
    private Long id;            //상품 코드
    private String itemNm;      // 상품 이름
    private Integer price;			// 상품 가격
    private Integer stockNumber;	// 재고 수량
    private String itemDetail;	// 상품 상세 설명
    private Integer viewCount;	// 상품 조회수

    public static ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setItemNm(item.getItemNm());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setStockNumber(item.getStockNumber());
        itemDTO.setItemDetail(item.getItemDetail());
        itemDTO.setViewCount(item.getViewCount());
        return itemDTO;
    }
}
