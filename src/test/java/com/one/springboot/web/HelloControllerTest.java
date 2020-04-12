package com.one.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) //테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자 실행
                            //여기서는 SpringRunner 라는 스프링 실행자를 사용한다.
                            //즉 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.

@WebMvcTest(controllers = HelloController.class)//여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션
                                                //선언시 @HelloController, @ControllerAdvice 등을 사용할 수 있다.



public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈 주입
    private MockMvc mvc; //스프링 mvc테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello" +
                "";

        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
        //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함. 체이닝이 지원되어 여러 검증기능을 이어 선언가능
        //isok() 같은 경우 200,404,500 등의 상태를 검증한다.
        //perform 의 결과 검증. 응답본문의 내용을 검증한다. controller 에서 hello리턴
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;
        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    } //param 은 API 테스트할때 사용될 요청 파라미터를 설정한다. 단 값은 String만 허용된다. 숫자 날짜 등의 데이터도
      //등록할때는 문자열로 변경해야 가능하다
}     //jsonPath는 JSON응답값을 필드별로 검증할 수 있는 메소드다. $를 기준으로 필드명 명시
