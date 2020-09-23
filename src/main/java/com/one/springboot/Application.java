package com.one.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.io.File;

//@EnableJpaAuditing //JPA Auditing 어노테이션을 모두 활성화시키기 위한 어노테이션.

@SpringBootApplication //해당 어노테이션으로 인해 스프링 부트의 자동 설정, 스프링 bean 읽기와 생성을 자동으로 설정되게 된다.
                        //여기부터 설정을 읽어가기 때문에, 이 클래스는 항상 프로젝트 최상단에 위치해야 한다.
public class Application {

    public static void main(String[] args) {
        //System.setProperty("Spring.devtools.livereload.enabled","true");

        SpringApplication.run(Application.class, args); //내장 was를 실행함. 외부에 별도로 톰캣등을 설치할 필요없이
                                                        // 스프링 부트로 만들어진 Jar파일로 실행하면 된다.
                                                        //스프링부트에서는 내장 was 사용을 권장하는데, 이유는 언제 어디에서나
                                                        //같은 환경에서 스프링부트를 배포할 수 있기 때문이다.



    }
}
