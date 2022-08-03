package com.onz.modules.review.infra;


import com.onz.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Company, Long>,
        ReviewRepositoryExtension {

}
