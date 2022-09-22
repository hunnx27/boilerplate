package com.onz.modules.admin.auth.infra;

import com.onz.modules.account.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAuthRepository extends JpaRepository<Admin, Long>{
}
