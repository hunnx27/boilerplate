package com.onz.modules.admin.auth.application;

import com.onz.common.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.Admin;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.admin.auth.domain.AdminCreateRequestDto;
import com.onz.modules.admin.auth.domain.AdminLonginRequestDto;
import com.onz.modules.admin.auth.infra.AdminAuthRepository;
import com.onz.modules.auth.application.util.CookieUtils;
import com.onz.modules.auth.application.util.JwtProvider;
import com.onz.modules.auth.application.util.MD5Utils;
import com.onz.modules.auth.application.util.MysqlSHA2Util;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.auth.web.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminAuthService {
    private final AccountRepository accountRepository;
    private final AdminAuthRepository adminAuthRepository;
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    public ResponseEntity<ApiR<?>> loginAdmin(HttpServletResponse response, AdminLonginRequestDto adminRequestDto) throws CustomException {
        Account accountId = accountRepository.findByEncodedUserId2(MysqlSHA2Util.getSHA512(adminRequestDto.getUserID())).get();
        if (accountId == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{adminRequestDto.getUserID()});
        } else {
            if (accountId.getPassword().equals(MD5Utils.getMD5(adminRequestDto.getPassWord()))) {
                if (accountId.getRole().name().equals("USER")) {
                    throw new CustomException(ErrorCode.NOT_ACCEPTABLE, new String[]{adminRequestDto.getUserID()});
                } else {
                    UserPrincipal.create(accountId);
                    UserDetails principal = userDetailsService.loadUserByUsername(MysqlSHA2Util.getSHA512(adminRequestDto.getUserID()));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    String token = jwtProvider.createToken(authentication);
                    response.setHeader("Authorization", token);
                    CookieUtils.addCookie(response, "Authorization", token, 180);
                    log.info(token);
                    return ResponseEntity.ok(ApiR.createSuccess(new AuthResponse(token)));
                }
            } else {
                throw new CustomException(ErrorCode.INVALID_PASSWORD);
            }
        }
    }

    public ResponseEntity<ApiR<Admin>> createAdmin(AdminCreateRequestDto adminCreateRequestDto) throws CustomException {
            if (accountRepository.existsAccountByUserId(MysqlSHA2Util.getSHA512(adminCreateRequestDto.getUserId()))) {
                //아마 디코드해야함 true
                throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
            } else {
                Account savedAccount = accountRepository.save(new Account(adminCreateRequestDto));
                Admin admin = new Admin(adminCreateRequestDto, savedAccount);
                return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(adminAuthRepository.save(admin)));
            }
    }
}
