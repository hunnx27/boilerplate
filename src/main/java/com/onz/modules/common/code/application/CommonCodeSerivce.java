package com.onz.modules.common.code.application;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.admin.companies.domain.Companies;
import com.onz.modules.admin.companies.web.dto.CompaniesFixCreateRequestDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.code.domain.CommonCode;
import com.onz.modules.common.code.infra.CommonJRepository;
import com.onz.modules.common.code.infra.CommonRepository;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.onz.modules.admin.companies.domain.QCompanies.companies;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonCodeSerivce {
    private final CommonRepository commonRepository;
    private final CommonJRepository commonJRepository;
    private final AccountRepository accountRepository;

    public Long save(CommonCode commonCode){
        return commonRepository.save(commonCode);
    }
    public CommonCode findById(Long id){
        return commonRepository.findById(id);
    }

    public void create(CommonCodeInitRequestDto initRequestDto) {
        CommonCode commonCode = CommonCode.builder()
                .codeName(initRequestDto.getCodeName())
                        .codeSebu(initRequestDto.getCodeSebu())
                                .code(initRequestDto.getCode())
                                        .useYn(YN.Y)
                                                .build();
        commonJRepository.save(commonCode);
        }
}
