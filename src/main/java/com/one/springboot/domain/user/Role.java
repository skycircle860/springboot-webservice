package com.one.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//스프링 시큐리티 관련 클래스-1
public enum  Role { //스프링 시큐리티에서는 권한코드에 항상 ROLE_이 앞에 있어야만 한다.그래서 코드별 키 값을 ROLE_ 형식으로 지정한다.
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자");

    private final String key;
    private final String title;
}
