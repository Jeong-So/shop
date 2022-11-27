// 상품 Entity 설계

package com.jeong.shop.entity;

import com.jeong.shop.constant.ItemSellStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name="item")
@Entity
public class ItemEntity {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)  // 기본키 생성전략 설정 : GenerationType.AUTO (JPA가 IDENTITY, SEQUENCE, TABLE 중 자동 선택)
    private Long itemId;                             // 상품 코드

    @Column(name="item_name", nullable = false, length = 50)
    private String itemName;                         // 상품명

    @Column(nullable = false)
    private int price;                               // 상품 가격

    @Column(name = "stock_number", nullable = false)
    private int stockNumber;                         // 재고수량

    @Lob                                             // 사이즈가 큰 데이터 외부 파일로 저장하기 위한 데이터 타입 매핑(CLOB)
    @Column(name = "item_detail", nullable = false)
    private String itemDetail;                       // 상품 상세설명


    @Column(name = "item_sell_status", nullable = false)
    @Enumerated(EnumType.STRING)                     // enum 타입 매핑 ; EnumType.ORDINAL : enum 순서 값을 DB에 저장, EnumType.STRING : enum 이름을 DB에 저장
    private ItemSellStatus itemSellStatus;           // 상품 판매 상태

    @Column(name = "reg_time", nullable = false)
    private LocalDateTime regTime;                   // 등록 시간

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;                // 수정 시간
}
