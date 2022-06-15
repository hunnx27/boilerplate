package com.onz.modules.organization.infra;


import com.onz.modules.organization.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long>,
    OrganizationRepositoryExtension {

}
