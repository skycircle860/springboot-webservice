package com.one.springboot.web.dto;

import com.one.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    //Entity 클래스와 비슷한데 Dto 클래스를 추가로 생성한 이유는 Entity 클래스는 데이터베이스와 맞닿은 핵심클래스이고, 해당 클래스를 기준으로 테이블이 생성되며 스키마가 변경되는
    //클래스인데 화면을 변경하기 위해 Entity 클래스를 변경하는것은 너무 큰 변경이라 그런 것이다.
    //dto 는 view 를 위한 클래스이고 view 는 잦은 수정이 필요하므로  dto 클래스를 수정하는 것이 Entity 클래스를 수정하는 것보다 일이 줄어든다.
    //View Layer 와 DB Layer 의 역할 분리를 철저하게 하는 것이 좋다.
}
