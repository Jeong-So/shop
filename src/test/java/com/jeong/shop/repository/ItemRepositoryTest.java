package com.jeong.shop.repository;

import com.jeong.shop.constant.ItemSellStatus;
import com.jeong.shop.entity.ItemEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션, 실제 어플리케이션을 구동할 때처럼 모든 Bean을 IoC 컨테이너에 등록
@TestPropertySource(locations="classpath:application-test.properties")  // 설정 파일 application-test.properties에 우선순위 부여 : Test DB로 H2를 사용
class ItemRepositoryTest {

    @Autowired   // itemRepository를 사용하기 위하여 @Autowired 어노테이션을 이용하여 Bean 주입
    ItemRepository itemRepository;

    @Test   // 테스트할 대상 지정 : 테스트할 메소드 위에 선언
    @DisplayName("상품 저장 테스트")   // Junit5에 추가된 어노테이션으로 테스트 코드 실행 시 @DisplayName에 지정한 테스트명 노출
    public void createItemTest() {
        ItemEntity item = new ItemEntity();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setStockNumber(100);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        ItemEntity savedItem = itemRepository.save(item);  // itemRepository가 상속받은 JpaRepository(CrudRepository) 인터페이스의 save 메소드 동작
        System.out.println(savedItem.toString());
    }

}