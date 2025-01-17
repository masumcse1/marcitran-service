package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.JourneyLogDTO;
import com.ufril.medtran.dto.dispatch.ShiftDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.CheckList;
import com.ufril.medtran.persistence.domain.common.Location;
import com.ufril.medtran.persistence.domain.common.Station;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.service.*;
import com.ufril.medtran.util.MapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController(value = "shiftResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "shift")
public class ShiftResource {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ServiceLevelService serviceLevelService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CommonService commonService;

    @ApiOperation(
            value = "Get All Shift",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getAllShift/{companyId}/{status}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllShift(@PathVariable("companyId") Integer companyId,
                                         @PathVariable("status") final Integer status,
                                         @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<Shifts> shifts = shiftService.getAllShifts(companyId, status, pageable);
        return new ResponseEntity<>(new Response(StatusType.OK, shifts), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All Shift By date range",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getAllShiftsByDate/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllShiftsByDate(@PathVariable("companyId") Integer companyId,
                                                Date fromDate, Date toDate) {

        List<Shifts> shiftList = shiftService.getAllShiftsByDate(companyId, fromDate, toDate);
        return new ResponseEntity<>(new Response(StatusType.OK, shiftList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All Shift",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getAllShiftsByDispatch/{dispatchId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllShiftsByDispatch(@PathVariable("dispatchId") int dispatchId) {
        List<Shifts> shiftList = shiftService.getAllShiftsByDispatch(dispatchId);
        return new ResponseEntity<>(new Response(StatusType.OK, shiftList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Shift By ShiftId",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getShiftById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getShiftById(@PathVariable("id") final int id) {
        Shifts shift = shiftService.getShiftById(id);

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
        dto.setCompanyId(shift.getCompanyId());

        List<Integer> employees = new ArrayList<>();
        for (ShiftCrewMembers crewMember : shift.getShiftCrewMembers()) {
            employees.add(crewMember.getEmployeeID().getId());
        }
        dto.setEmployees(employees);

        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Shift",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/createShift",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createShift(@RequestBody ShiftDTO shiftDTO) {
        Shifts shift = MapperUtils.mapDTOToShift(shiftDTO);

        ServiceLevel serviceLevel = serviceLevelService.getServiceLevelById(shiftDTO.getEffServiceLevel());
        shift.setEffServiceLevel(serviceLevel);

        Vehicles vehicle = vehicleService.getVehicleById(shiftDTO.getVehicle());
        shift.setVehicle(vehicle);

        shift = shiftService.createShift(shift);

        List<ShiftCrewMembers> crewMembers = new ArrayList<>();

        for (int employeeID : shiftDTO.getEmployees()) {
            ShiftCrewMembers member = new ShiftCrewMembers();
            member.setEmployeeID(employeeService.getEmployeeById(employeeID));
            member.setShiftID(shift);
            crewMembers.add(member);
        }

        shiftService.mapShiftCrewMembers(crewMembers);

        return new ResponseEntity<>(new Response(StatusType.OK, shift), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Update an existing Shift",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update new Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/updateShift",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateShift(@RequestBody ShiftDTO shiftDTO) {
        Shifts shift = MapperUtils.mapDTOToShift(shiftDTO);

        ServiceLevel serviceLevel = serviceLevelService.getServiceLevelById(shiftDTO.getEffServiceLevel());
        shift.setEffServiceLevel(serviceLevel);

        Vehicles vehicle = vehicleService.getVehicleById(shiftDTO.getVehicle());
        shift.setVehicle(vehicle);
        shift = shiftService.updateShift(shift);

        return new ResponseEntity<>(new Response(StatusType.OK, shift), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing Shift",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Shift", response = Response.class)
    })
    @RequestMapping(
            value = "/Shift/deleteShift/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteShift(@PathVariable("id") final int id) {
        boolean flag = shiftService.deleteShift(id);
        return new ResponseEntity<>(new Response(StatusType.OK, flag), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/Shift/addJourneyLog",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> addJourneyLog(@RequestBody List<JourneyLogDTO> journeyLogList) {
        for (JourneyLogDTO dto : journeyLogList) {
            JourneyLogs journeyLogs = MapperUtils.mapDTOToJourneyLog(dto);

            Dispatches dispatches = new Dispatches();
            dispatches.setId(dto.getDispatchId());
            journeyLogs.setDispatches(dispatches);

            Shifts shifts = new Shifts();
            shifts.setId(dto.getShiftId());
            journeyLogs.setShift(shifts);

            Vehicles vehicles = new Vehicles();
            vehicles.setId(dto.getVehicleId());
            journeyLogs.setVehicle(vehicles);

            shiftService.addJourneyLogs(journeyLogs);
        }

        return new ResponseEntity<>(new Response(StatusType.OK, journeyLogList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All Stations",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all station", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getAllStations",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllStations() {
        List<Station> stationList = shiftService.getAllStations();
        return new ResponseEntity<>(new Response(StatusType.OK, stationList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All Locations",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all locations", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getAllLocations",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllLocations() {
        List<Location> locationList = shiftService.getAllLocations();
        return new ResponseEntity<>(new Response(StatusType.OK, locationList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All checklist",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all checklist", response = Response.class)
    })
    @RequestMapping(
            value = "/shift/getAllCheckList",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllCheckList() {
        List<CheckList> checklist = shiftService.getAllCheckList();
        return new ResponseEntity<>(new Response(StatusType.OK, checklist), HttpStatus.OK);
    }
}
