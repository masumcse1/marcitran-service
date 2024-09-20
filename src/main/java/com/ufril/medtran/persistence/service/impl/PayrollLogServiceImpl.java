package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.account.PayRollLog;
import com.ufril.medtran.persistence.repository.account.PayrollLogRepository;
import com.ufril.medtran.persistence.service.PayrollLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PayrollLogServiceImpl implements PayrollLogService {

    @Autowired
    private PayrollLogRepository payrollLogRepository;
    @Override
    public PayRollLog getPayrollLogById(int id) {
        return payrollLogRepository.findOne(id);
    }

    @Override
    @Transactional
    public PayRollLog createPayrollLog(PayRollLog payRollLog) {
        return payrollLogRepository.save(payRollLog);
    }

    @Override
    @Transactional
    public PayRollLog updatePayrollLog(PayRollLog payRollLog) {
        return payrollLogRepository.save(payRollLog);
    }

    @Override
    @Transactional
    public boolean deletePayrollLog(int id) {
        payrollLogRepository.delete(id);
        return true;
    }
}
