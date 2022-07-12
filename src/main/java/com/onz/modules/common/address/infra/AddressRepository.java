package com.onz.modules.common.address.infra;

import com.onz.modules.common.address.domain.AddressCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressCode, Long>,
        AddressRepositoryExtension {

//    Optional<Account> findByUserId(String userId);

}