package com.jeong.shop.repository;

import com.jeong.shop.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository에는 기본적인 CRUD 및 페이징 처리를 위한 메소드 정의
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
