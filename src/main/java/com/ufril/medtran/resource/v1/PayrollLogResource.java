package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.PayRollLog;
import com.ufril.medtran.persistence.service.EmpCertService;
import com.ufril.medtran.persistence.service.EmployeeService;
import com.ufril.medtran.persistence.service.PayrollLogService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "payrollLogResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "payrollLog")
public class PayrollLogResource {
    private static Logger logger = Logger.getLogger(PayrollLogResource.class);

    @Autowired
    private PayrollLogService payrollLogService;

    @RequestMapping(value = "/payrollLog/getPayrollLogId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPayrollLogByEmployeeId(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, payrollLogService.getPayrollLogById(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/payrollLog/createPayrollLog", method = RequestMethod.POST)
    public ResponseEntity<?> createPayrollLog(PayRollLog payRollLog) {
        return new ResponseEntity<>(new Response(StatusType.OK, payrollLogService.createPayrollLog(payRollLog)), HttpStatus.OK);
    }

    @RequestMapping(value = "/payrollLog/updatePayrollLog", method = RequestMethod.POST)
    public ResponseEntity<?> updatePayrollLog(PayRollLog payRollLog) {
        return new ResponseEntity<>(new Response(StatusType.OK, payrollLogService.updatePayrollLog(payRollLog)), HttpStatus.OK);
    }

    @RequestMapping(value = "/payrollLog/deletePayrollLog/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deletePayrollLog(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, payrollLogService.deletePayrollLog(id)), HttpStatus.OK);
    }
}
