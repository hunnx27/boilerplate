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

        if(ObjectUtils.isEmpty(oAuth2UserInfo.getId())) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }
        AuthProvider snsType = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId());
//        Optional<Account> userOptional = accountRepository.findByPlainUserId(oAuth2UserInfo.getId(), snsType);
//        Optional<Account> userOptional = accountRepository.findByPlainUserId2(oAuth2UserInfo.getId());
        Optional<Account> userOptional = accountRepository.findByPlainUserId3(oAuth2UserInfo.getId());
        Account account;
        if(userOptional.isPresent()) {
            account = userOptional.get();

//            // ?????? ???????????? provider??? ???????????? ???????????? provider??? ????????? ?????? ??????!
//            if(!account.getSnsType().equals(snsType)) {
//                log.error("????????? ?????????????????? ?????? ?????????????????? ????????????????????????.");
//                throw new OAuth2AuthenticationException("Looks like you're signed up with " +
//                        account.getSnsType() + " account. Please use your " + account.getSnsType() +
//                        " account to login.");
//            }

            account = updateExistingUser(account, oAuth2UserInfo);
        } else {
            // ?????? ????????? ??????(Step, ID????????????.)
            account = getNewUser(oAuth2UserRequest, oAuth2UserInfo);

        }
        return UserPrincipal.create(account, oAuth2User.getAttributes());
    }
    private Account getNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {

        Account user = Account.builder()
                .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                .userId(oAuth2UserInfo.getId())
                //.gubn(Gubn.TEACHER)
                .role(Role.USER)
                .build();
//        return accountRepository.save(user);
        return user;
    }

    // ?????? USER -> OAuth2 ???????????? ????????????..
    private Account updateExistingUser(Account existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.update(oAuth2UserInfo.getrRegistrationId());
        return accountRepository.save(existingUser);
    }

}
