package com.onz.modules.common.pointHistory.application;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.domain.embed.PointInfo;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.common.pointHistory.infra.PointHistoryRepository;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PointHistoryService {

    private final PointHistoryRepository pointRepository;
    private final AccountRepository accountRepository;

    // 이력 조회
    public Page<PointHistory> list(PointHistorySearchRequest poSearchRequest) {
        return pointRepository.list(poSearchRequest);
    }

    // 이력 생성
    public PointHistory create() {
        Optional<Account> account = accountRepository.findById(1l);
        PointHistory ph = PointHistory.builder()
                .account(account.get())
                .pointTable(PointTable.WELCOME_JOIN)
                .build();
        PointHistory rs = pointRepository.save(ph);
        return rs;
    }
}
