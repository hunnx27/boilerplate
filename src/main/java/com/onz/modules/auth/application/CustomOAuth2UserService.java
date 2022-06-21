package com.onz.modules.auth.application;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.UserAccount;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.auth.web.dto.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountRepository accountRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest
            .getClientRegistration()
            .getProviderDetails()
            .getUserInfoEndpoint()
            .getUserNameAttributeName();

        OAuthAttributes of = OAuthAttributes.of(registrationId, userNameAttributeName,
            oAuth2User.getAttributes());

        Account account = saveOrUpdate(of);

        return UserAccount.to(account);
//        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(Role.USER.name())),
//                of.getAttributes(),
//                of.getNameAttributeKey());
    }

    private Account saveOrUpdate(OAuthAttributes of) {
        Account account = Optional.ofNullable(accountRepository.findByEmail(of.getEmail()))
            .map(x -> x.update(of.getName(), of.getPicture()))
            .orElse(of.toEntity());
        return accountRepository.save(account);
    }

}
