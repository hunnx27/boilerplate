package com.onz.modules.common.pointHistory.infra;


import com.onz.modules.common.pointHistory.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long>,
        PointHistoryRepositoryExtension {

}
