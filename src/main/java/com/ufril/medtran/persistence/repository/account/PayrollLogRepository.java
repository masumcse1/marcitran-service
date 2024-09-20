package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.PayRollLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollLogRepository extends JpaRepository<PayRollLog, Integer> {
}
