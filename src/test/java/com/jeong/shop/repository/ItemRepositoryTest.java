package com.jeong.shop.repository;

import com.jeong.shop.constant.ItemSellStatus;
import com.jeong.shop.entity.ItemEntity;
import com.jeong.shop.entity.QItemEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Test   // 테스트할 대상 지정 : 테스트할 메소드 위에 선언
    @DisplayName("상품 저장 테스트2")   // Junit5에 추가된 어노테이션으로 테스트 코드 실행 시 @DisplayName에 지정한 테스트명 노출
    public void createItemTest2() {
        for(int i=1; i<=5; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemName("테스트 상품"+ i );
            item.setPrice(10000+ i);
            item.setStockNumber(100 - i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            ItemEntity savedItem = itemRepository.save(item);  // itemRepository가 상속받은 JpaRepository(CrudRepository) 인터페이스의 save 메소드 동작
//            System.out.println(savedItem.toString());
        }

        for(int i=6; i<=10; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemName("테스트 상품"+ i );
            item.setPrice(10000+ i);
            item.setStockNumber(100 - i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            ItemEntity savedItem = itemRepository.save(item);  // itemRepository가 상속받은 JpaRepository(CrudRepository) 인터페이스의 save 메소드 동작
//            System.out.println(savedItem.toString());
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

    // Querydsl 조회테스트 1
    @PersistenceContext // 영속성 컨텍스트를 사용하기 위해 @PersistenceContext를 이용해 EntityManager 빈을 주입
    EntityManager em;

    @Test
    @DisplayName("Querydsl 조회테스트 1")
    public void queryDslTest() {
        this.createItemTest();
        // JPAQueryFactory를 이용하여 쿼리를 동적으로 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        // Querydsl을 통해 쿼리를 생성하기 위해 플러그인을 통해 자동으로 생성된 QItemEntity 객체 이용
        QItemEntity qItemEntity = QItemEntity.itemEntity;
        // JAVA 소스코드지만 SQL문과 비슷하게 소스 작성
        JPAQuery<ItemEntity> query = queryFactory.selectFrom(qItemEntity)
                .where(qItemEntity.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItemEntity.itemDetail.like("%"+"테스트 상품 상세 설명"+"%"))
                .orderBy(qItemEntity.price.desc());

        // JPAQuery 메소드 중 하나인 fetch를 이용해서 쿼리 결과를 리스트로 반환
        // fetch() 메소드 실행 시점에 쿼리문 실행
        List<ItemEntity> itemList = query.fetch();

        for(ItemEntity itemEntity : itemList) {
            System.out.println(itemEntity.toString());
        }
    }

    // 상품 Querydsl 조회 테스트 2_QuerydslPredicateExecutor
    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2_QuerydslPredicateExecutor")
    public void queryDslTest2(){

        this.createItemTest2();

        // BooleanBuilder : 쿼리에 들어갈 조건을 만들어주는 빌더
        // Predicate를 구현하고 있으며 메소드 체인 형식으로 사용가능
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        // Querydsl을 통해 쿼리를 생성하기 위해 플러그인을 통해 자동으로 생성된 QItemEntity 객체 이용
        QItemEntity itemEntity = QItemEntity.itemEntity;

        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        // 필요한 상품을 조회하는데 필요한 "and" 조건을 만들어주는 빌더
        booleanBuilder.and(itemEntity.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(itemEntity.price.gt(price)); // price= 10003 초과 상품 찾기
        // Prediate를 구현하고 있으며, 아래 소스에서는 상품 판매상태가 SELL일 때만
        // booleanBuilder에 판매상태 조건을 동적으로 추가
        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(itemEntity.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        // 데이터를 페이징해 조회하도록 PageRequest.of() 메소드를 이용해 Pageable 객체를 생성
        // PageRequest.of(첫번째 인자: 조회할 페이지 번호, 두번째 인자: 한 페이지당 조회할 데이터 개수);
        Pageable pageable = PageRequest.of(0,5);
        // QueryDslPredicateExecutor 인터페이스에서 정의한 findAll() 메소드를 이용해 조건에 맞는 데이터를 Page 객체로 받아옴
        Page<ItemEntity> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPagingResult.getTotalElements());

        List<ItemEntity> resultItemList = itemPagingResult.getContent();
        for(ItemEntity resultItem: resultItemList){
            System.out.println(resultItem.toString());
        }

    }


}