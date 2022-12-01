package com.jeong.shop.repository;

import com.jeong.shop.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository에는 기본적인 CRUD 및 페이징 처리를 위한 메소드 정의
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    // #1 쿼리 메소드 실습
    // 쿼리 메소드를 이용한 상품 조회 실습
    List<ItemEntity> findByItemName(String itemName);

    // 상품명, 상품상세설명 OR 조건 실습
    List<ItemEntity> findByItemNameOrItemDetail(String itemNm, String itemDetail);

    // 상품명, 상품상세설명 And 조건 실습
    List<ItemEntity> findByItemNameAndItemDetail(String itemNm, String itemDetail);

    // 가격 LessThan 실습
    List<ItemEntity> findByPriceLessThan(Integer price);

    // 가격 LessThan 및 가격 내림차순 조회 실습
    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(Integer price);


    // #2 @Query 어노테이션 실습
    // @Query 상품 데이터 조회 실습
    @Query("select i from ItemEntity i where i.itemDetail like %:itemDetail% " +
            "order by i.price desc")
    List<ItemEntity> findByItemDetail(@Param("itemDetail") String itemDetail);

    // @Query-nativeQuery를 이용한 상품 조회 실습
    // nativeQuery : 기존의 데이터베이스에서 사용하던 쿼리를 그대로 사용해야 할 때 사용
    // But, 특정 데이터베이스에 종속됨
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% " +
            "order by i.price desc", nativeQuery = true)
    List<ItemEntity> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
