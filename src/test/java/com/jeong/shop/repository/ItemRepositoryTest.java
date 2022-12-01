package com.jeong.shop.repository;

import com.jeong.shop.constant.ItemSellStatus;
import com.jeong.shop.entity.ItemEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest  // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션, 실제 어플리케이션을 구동할 때처럼 모든 Bean을 IoC 컨테이너에 등록
@TestPropertySource(locations="classpath:application-test.properties")  // 설정 파일 application-test.properties에 우선순위 부여 : Test DB로 H2를 사용
class ItemRepositoryTest {

    @Autowired   // itemRepository를 사용하기 위하여 @Autowired 어노테이션을 이용하여 Bean 주입
    ItemRepository itemRepository;

    @Test   // 테스트할 대상 지정 : 테스트할 메소드 위에 선언
    @DisplayName("상품 저장 테스트")   // Junit5에 추가된 어노테이션으로 테스트 코드 실행 시 @DisplayName에 지정한 테스트명 노출
    public void createItemTest() {
        for(int i=1; i<=10; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemName("테스트 상품"+ i );
            item.setPrice(10000+ i);
            item.setStockNumber(100 - i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            ItemEntity savedItem = itemRepository.save(item);  // itemRepository가 상속받은 JpaRepository(CrudRepository) 인터페이스의 save 메소드 동작
            System.out.println(savedItem.toString());
        }
    }

    // #1 쿼리 메소드 테스트
    // 쿼리 메소드를 이용한 상품 조회 테스트
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNameTest() {
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByItemName("테스트 상품3");
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNameOrItemDetailTest(){
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByItemNameOrItemDetail("테스트 상품2", "테스트 상품 상세 설명5");
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 And 테스트")
    public void findByItemNameAndItemDetailTest(){
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByItemNameAndItemDetail("테스트 상품2", "테스트 상품 상세 설명5");
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByPriceLessThan(10004);
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDescTest(){
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10004);
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    // #2 @Query 테스트
    // @Query를 이용한 상품 조회 테스트
    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    // @Query-nativeQuery를 이용한 상품 조회 테스트
    @Test
    @DisplayName("@Query-nativeQuery를 이용한 상품 조회 테스트")
    public void findByItemDetailByNativeTest(){
        this.createItemTest(); // 10개의 상품 저장하는 테스트코드 실행
        List<ItemEntity> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
        System.out.println("Test");
        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }


}