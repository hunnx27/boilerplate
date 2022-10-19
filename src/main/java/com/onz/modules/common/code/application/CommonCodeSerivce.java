package com.onz.modules.common.code.application;

import com.onz.modules.common.code.domain.CommonCode;
import com.onz.modules.common.code.infra.CommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonCodeSerivce {
    private final CommonRepository commonRepository;

    public Long save(CommonCode commonCode){
        return commonRepository.save(commonCode);
    }
    public CommonCode findById(Long id){
        return commonRepository.findById(id);
    }
}
