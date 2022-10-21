package com.onz.modules.admin.counsels.infra;

import com.onz.modules.counsel.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselsRepository extends JpaRepository<Counsel, Long>, CounselsRepositoryExtension {
}
