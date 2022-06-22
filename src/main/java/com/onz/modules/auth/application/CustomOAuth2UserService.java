package com.onz.modules.auth.application;

import com.onz.common.enums.Role;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.auth.application.util.OAuth2UserInfoFactory;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.auth.web.dto.oauth.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(ObjectUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }
        Optional<Account> userOptional = accountRepository.findByEmail(oAuth2UserInfo.getEmail());
        Account account;
        if(userOptional.isPresent()) {
            account = userOptional.get();

            // 신규 사용자의 provider와 기등록된 사용자의 provider가 다르면 오류 출력!
            if(!account.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationException("Looks like you're signed up with " +
                        account.getProvider() + " account. Please use your " + account.getProvider() +
                        " account to login.");
            }

            account = updateExistingUser(account, oAuth2UserInfo);
        } else {
            // 신규 유저면 등록
            account = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(account, oAuth2User.getAttributes());
    }
    private Account registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {

        Account user = Account.builder()
                .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                .name(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .picture(oAuth2UserInfo.getPicture())
                .role(Role.USER)
                .build();
        return accountRepository.save(user);
    }

    // 기존 USER -> OAuth2 방식으로 덮어쓰기..
    private Account updateExistingUser(Account existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.update(oAuth2UserInfo.getName(), oAuth2UserInfo.getPicture());
        return accountRepository.save(existingUser);
    }

}
