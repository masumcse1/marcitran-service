package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.account.PayRollLog;
import com.ufril.medtran.persistence.service.PayrollLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "payrollLogResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "payrollLog")
public class PayrollLogResource {

    @Autowired
    private PayrollLogService payrollLogService;

    @RequestMapping(value = "/payrollLog/getPayrollLogId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPayrollLogByEmployeeId(@PathVariable("id") final int id) {
        PayRollLog payRollLog = payrollLogService.getPayrollLogById(id);
        payRollLog.getEmployeeID().setUser(null);
        payRollLog.getEmployeeID().setEmployeeCertificates(null);
        return new ResponseEntity<>(new Response(StatusType.OK, payRollLog), HttpStatus.OK);
    }

    @RequestMapping(value = "/payrollLog/createPayrollLog", method = RequestMethod.POST)
    public ResponseEntity<?> createPayrollLog(@RequestBody PayRollLog payRollLog) {
        payRollLog = payrollLogService.createPayrollLog(payRollLog);
        return new ResponseEntity<>(new Response(StatusType.OK, payRollLog), HttpStatus.OK);
    }

    @RequestMapping(value = "/payrollLog/updatePayrollLog", method = RequestMethod.POST)
    public ResponseEntity<?> updatePayrollLog(@RequestBody PayRollLog payRollLog) {
        payRollLog = payrollLogService.updatePayrollLog(payRollLog);
        payRollLog.getEmployeeID().setUser(null);
        payRollLog.getEmployeeID().setEmployeeCertificates(null);
        return new ResponseEntity<>(new Response(StatusType.OK, payRollLog), HttpStatus.OK);
    }

    @RequestMapping(value = "/payrollLog/deletePayrollLog/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deletePayrollLog(@PathVariable("id") final int id) {
        boolean isDeleted = payrollLogService.deletePayrollLog(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }
}
