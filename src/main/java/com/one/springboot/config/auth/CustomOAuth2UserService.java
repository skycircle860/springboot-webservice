package com.one.springboot.config.auth;

import com.one.springboot.config.auth.dto.OAuthAttributes;
import com.one.springboot.config.auth.dto.SessionUser;
import com.one.springboot.domain.user.User;
import com.one.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


@RequiredArgsConstructor
@Service
//스프링 시큐리티 관련 클래스-3
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("check  CustomOAuth2UserService 3class   "  );
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    //현재 로그인 진행 중인 서비스를 구분하는 코드. 이후 네이버 로그인인지 구글로그인인지 구분할때 사용
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName(); //oauth2 로그인 진행시 키가 되는 필드값. pk와 같은 의미. 구글의 경우 기본적으로 코드를 지원하나, 네이버나 카카오등은
                                                                    //지원하지 않는다. 구글의 기본코드는 sub. 이후 네이버와 구글 로그인을 동시 지원할 때 사용
        System.out.println("check       CustomOAuth2UserService        "+userNameAttributeName);
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스. 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용한다.

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); //SessionUser 세션에 사용자 정보를 저장하기 위한 Dto 클래스.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
