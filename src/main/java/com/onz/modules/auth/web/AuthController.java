package com.onz.modules.auth.web;

import com.onz.common.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import com.onz.modules.auth.application.util.CookieUtils;
import com.onz.modules.auth.application.UserDetailServiceImpl;
import com.onz.modules.auth.application.util.JwtProvider;
import com.onz.modules.auth.web.dto.request.LoginRequest;
import com.onz.modules.auth.web.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
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
public class AuthController {

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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
}
