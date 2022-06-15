package com.onz.modules.review.infra;


import com.onz.modules.organization.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Organization, Long>,
        ReviewRepositoryExtension {

}
