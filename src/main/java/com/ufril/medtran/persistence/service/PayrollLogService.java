package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.PayRollLog;

import java.util.List;

public interface PayrollLogService {
    PayRollLog getPayrollLogById(int id);
    PayRollLog createPayrollLog(PayRollLog payRollLog);
    PayRollLog updatePayrollLog(PayRollLog payRollLog);
    boolean deletePayrollLog(int id);
}
