package com.study.shop.domain.item;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품명으로 상품 정보 조회 (select * from shop.item where item_Nm=?)
    Optional<Item> findByItemNm(String itemNm);

    // update shop.item set viewCount=viewCount+1 where id=?
    @Modifying
    @Query("update Item i set i.viewCount = i.viewCount+1 where i.id=:id")
    void updateViewCount(@Param("id")Long id);

    Item findItemById(Long id);
}
