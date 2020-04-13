package com.one.springboot.config.auth.dto;

import com.one.springboot.domain.user.User;
import lombok.Getter;
import java.io.Serializable;

@Getter
//인증된 사용자의 정보만 필요.
public class SessionUser implements Serializable{
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        System.out.println("check SessionUser");
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getEmail();
    }
}
