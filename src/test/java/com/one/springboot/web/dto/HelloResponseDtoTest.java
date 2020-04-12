package com.one.springboot.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() { //테스트 실행시 final 필드 에러가 발생한다면 gradle 버전이 4.10이 아닌 5.x 인지 확인
                                    //5.x 버전일경우 alt+f12로 콘솔창 열고 gradlew wrapper --gradle-version 4.10.2
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
