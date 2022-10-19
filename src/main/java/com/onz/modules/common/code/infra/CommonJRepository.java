package com.onz.modules.common.code.infra;

import com.onz.modules.common.code.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonJRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findAllByCode(String code);
}
