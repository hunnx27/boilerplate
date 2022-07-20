package com.onz.modules.counsel.infra;


import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.organization.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselRepository extends JpaRepository<Counsel, Long>,
        CounselRepositoryExtension {

}
