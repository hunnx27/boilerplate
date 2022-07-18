package com.onz.modules.common.address.infra;

import com.onz.modules.common.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>,
        AddressRepositoryExtension {

//    Optional<Account> findByUserId(String userId);

}