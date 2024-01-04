package com.study.shop.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품명으로 상품 정보 조회 (select * from shop.item where item_Nm=?)
    Optional<Item> findByItemNm(String itemNm);
}
