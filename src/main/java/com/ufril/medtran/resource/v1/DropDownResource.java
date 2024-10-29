package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.ShiftDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.domain.patient.Patients;
import com.ufril.medtran.persistence.service.DropDownService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "dropdownResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "dropdown")
public class DropDownResource {

    @Autowired
    private DropDownService dropDownService;

    @RequestMapping(value = "/dropdown/getDispatches/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDispatchesByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<Dispatches> dispatches = dropDownService.getDispatchesByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, dispatches), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getShifts/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getShiftsByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<Shifts> shifts = dropDownService.getShiftsByCompanyId(companyId);
        List<ShiftDTO> shiftList = new ArrayList<>();

        for (Shifts shift : shifts) {
            ShiftDTO dto = new ShiftDTO();
            dto.setId(shift.getId());
            dto.setBasedFromLocation(shift.getBasedFromLocation());
            dto.setPostingLocation(shift.getPostingLocation());
            dto.setPrimaryRole(shift.getPrimaryRole());
            dto.setStartTime(shift.getStartTime());
            dto.setExpectedLength(shift.getExpectedLength());
            dto.setPrimaryCheckList(shift.getPrimaryCheckList());
            dto.setSecondaryCheckList(shift.getSecondaryCheckList());
            dto.setVehicle(shift.getVehicle().getId());
            dto.setVehicleCallSign(shift.getVehicle().getCallSign());
            dto.setEffServiceLevel(shift.getEffServiceLevel().getId());
            dto.setServiceLevelName(shift.getEffServiceLevel().getName());
            dto.setStatus(shift.getStatus());
            dto.setFuelLevel(shift.getFuelLevel());
            dto.setShiftType(shift.getShiftType());
            shiftList.add(dto);
        }
        return new ResponseEntity<>(new Response(StatusType.OK, shiftList), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getVehicles/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVehiclesByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<Vehicles> vehicles = dropDownService.getVehiclesByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicles), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getFacilities/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getFacilitiesByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<Facilities> facilities = dropDownService.getFacilitiesByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, facilities), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getPatients/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientsByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<Patients> patients = dropDownService.getPatientsByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, patients), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getServiceLevels/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getServiceLevelsByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<ServiceLevel> serviceLevels = dropDownService.getServiceLevelsByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, serviceLevels), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getEmployees/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeesByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<Employees> employees = dropDownService.getEmployeesByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, employees), HttpStatus.OK);
    }
}
