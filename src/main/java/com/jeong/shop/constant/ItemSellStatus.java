// 상품 판매가능상태를 나타내는 enum타입의 클래스
// enum 클래스 사용 : 1) 연관된 상수를 모아둘 수 있음  2) enum에 정의한 타입만 값으로 가지도록 컴파일시 체크

package com.jeong.shop.constant;

public enum ItemSellStatus {
    SELL, SOLD_OUT
}