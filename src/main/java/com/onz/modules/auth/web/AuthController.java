package com.onz.modules.auth.web;

import com.onz.common.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.application.util.CookieUtils;
import com.onz.modules.auth.application.UserDetailServiceImpl;
import com.onz.modules.auth.application.util.JwtProvider;
import com.onz.modules.auth.web.dto.request.LoginRequest;
import com.onz.modules.auth.web.dto.request.SignupRequest;
import com.onz.modules.auth.web.dto.response.AuthResponse;
import com.onz.modules.common.pointHistory.application.PointHistoryService;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final PointHistoryService pointHistoryService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(HttpServletResponse response,
        @RequestBody LoginRequest loginRequest) {

        UserDetails principal = userDetailService.loadUserByUsername(loginRequest.getName());
        if (!passwordEncoder.matches(loginRequest.getPassword(), principal.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
            principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        response.setHeader("Authorization", token);
        CookieUtils.addCookie(response, "Authorization", token, 180);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/auth/oauth2/signup")
    public ResponseEntity<?> oauth2Signup(HttpServletResponse response,
                                          @RequestBody SignupRequest signupRequest){

        // 0. 파라미터 확인
        log.info("id : {}", signupRequest.getSocialId());
        log.info("gubn : {}", signupRequest.getGubnCode());
        log.info("snstype {}", signupRequest.getSnsTypeCode());
        log.info("allCheckSignup {}", signupRequest.getAllCheckSignup());
        log.info("checkSignupService {}", signupRequest.getCheckSignupService());
        log.info("checkSignupPrivacy {}", signupRequest.getCheckSignupPrivacy());

        // 1. Account 등록
        Account account = accountService.getNewUser(signupRequest);

        // 2. 회원가입 후 서비스 처리
        afterJoinService(account);

        // 2. Account 조회
        UserDetails principal = userDetailService.loadUserByUsername(account.getUserId());

        // 3. Authentication 저장
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        // 4. 토큰생성
        String token = jwtProvider.createToken(authentication);

        // 5. 토큰반환
        response.setHeader("Authorization", token);
        CookieUtils.addCookie(response, "Authorization", token, 180);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * 회원가입후 서비스 처리
     */
    private void afterJoinService(Account account){
        // 회원가입 최초 포인트(+3000point)
        accountService.createMyPointHistories(account, PointTable.WELCOME_JOIN);
    }
}
