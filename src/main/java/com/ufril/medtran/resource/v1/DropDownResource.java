package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.ShiftDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.service.DropDownService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "dropdownResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "dropdown")
public class DropDownResource {
    private static Logger logger = Logger.getLogger(DispatchResource.class);

    @Autowired
    private DropDownService dropDownService;

    @RequestMapping(value = "/dropdown/getDispatches", method = RequestMethod.GET)
    public ResponseEntity<?> getDispatches() {
        return new ResponseEntity<>(new Response(StatusType.OK, dropDownService.getDispatches()), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getShifts", method = RequestMethod.GET)
    public ResponseEntity<?> getShifts() {
        List<Shifts> shifts = dropDownService.getShifts();
        List<ShiftDTO> shiftList = new ArrayList<>();

        for(Shifts shift : shifts){
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

    @RequestMapping(value = "/dropdown/getVehicles", method = RequestMethod.GET)
    public ResponseEntity<?> getVehicles() {
        return new ResponseEntity<>(new Response(StatusType.OK, dropDownService.getVehicles()), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getFacilities", method = RequestMethod.GET)
    public ResponseEntity<?> getFacilities() {
        return new ResponseEntity<>(new Response(StatusType.OK, dropDownService.getFacilities()), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getPatients", method = RequestMethod.GET)
    public ResponseEntity<?> getPatients() {
        return new ResponseEntity<>(new Response(StatusType.OK, dropDownService.getPatients()), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getServiceLevels", method = RequestMethod.GET)
    public ResponseEntity<?> getServiceLevels() {
        return new ResponseEntity<>(new Response(StatusType.OK, dropDownService.getServiceLevels()), HttpStatus.OK);
    }

    @RequestMapping(value = "/dropdown/getEmployees", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployees() {
        return new ResponseEntity<>(new Response(StatusType.OK, dropDownService.getEmployees()), HttpStatus.OK);
    }
}
