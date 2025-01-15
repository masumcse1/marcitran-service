package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.account.TimeClockDTO;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.TimeClock;
import com.ufril.medtran.persistence.service.EmployeeService;
import com.ufril.medtran.persistence.service.TimeClockService;
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

@RestController(value = "timeClockResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "timeClock")
public class TimeClockResource {

    @Autowired
    private TimeClockService timeClockService;

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(
            value = "Get All TimeClock",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/getAllTimeClock/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllTimeClock(@PathVariable("companyId") Integer companyId,
                                             @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);

        List<TimeClock> timeClockList = timeClockService.getAllTimeClock(companyId, pageable);
        List<TimeClockDTO> dtoList = new ArrayList<>();

        for (TimeClock timeClock : timeClockList) {
            TimeClockDTO dto = new TimeClockDTO();

            dto.setId(timeClock.getId());
            dto.setEmployeeId(timeClock.getEmployee().getId());
            dto.setFlag(timeClock.isFlag());
            dto.setPunctuality(timeClock.getPunctuality());
            dto.setClockIn(timeClock.getClockIn());
            dto.setClockOut(timeClock.getClockOut());
            dto.setComment(timeClock.getComment());
            dto.setCompanyId(timeClock.getCompanyId());

            dtoList.add(dto);
        }

        return new ResponseEntity<>(new Response(StatusType.OK, dtoList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a TimeClock By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/getTimeClockById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTimeClockById(@PathVariable("id") final int id) {
        TimeClock timeClock = timeClockService.getTimeClockById(id);
        TimeClockDTO dto = new TimeClockDTO();

        dto.setId(timeClock.getId());
        dto.setEmployeeId(timeClock.getEmployee().getId());
        dto.setFlag(timeClock.isFlag());
        dto.setPunctuality(timeClock.getPunctuality());
        dto.setClockIn(timeClock.getClockIn());
        dto.setClockOut(timeClock.getClockOut());
        dto.setComment(timeClock.getComment());
        dto.setCompanyId(timeClock.getCompanyId());

        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a TimeClock By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/isTimeClockExists/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> isTimeClockExists(@PathVariable("id") final int employeeID) {
        Employees employee = employeeService.getEmployeeById(employeeID);
        List<TimeClock> timeClocks = timeClockService.getTimeClockByEmployeeAndDate(employee, new Date());
        return new ResponseEntity<>(new Response(StatusType.OK, !timeClocks.isEmpty()), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all TimeClock By Date",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/getTimeClockByToday/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getTimeClockByToday(@PathVariable("companyId") Integer companyId) {
        List<TimeClock> timeClocks = timeClockService.getTimeClockByDate(companyId, new Date());
        return new ResponseEntity<>(new Response(StatusType.OK, timeClocks), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new TimeClock",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/createTimeClock",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createTimeClock(@RequestBody TimeClockDTO timeClockDTO) {
        TimeClock timeClock = MapperUtils.mapDTOToTimeClock(timeClockDTO);

        Employees employee = employeeService.getEmployeeById(timeClockDTO.getEmployeeId());
        timeClock.setEmployee(employee);

        timeClock = timeClockService.createTimeClock(timeClock);
        timeClockDTO.setId(timeClock.getId());

        return new ResponseEntity<>(new Response(StatusType.OK, timeClockDTO), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing TimeClock",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/updateTimeClock",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateTimeClock(@RequestBody TimeClock timeClock) {
        timeClock = timeClockService.updateTimeClock(timeClock);
        TimeClockDTO dto = new TimeClockDTO();

        dto.setId(timeClock.getId());
        dto.setEmployeeId(timeClock.getEmployee().getId());
        dto.setFlag(timeClock.isFlag());
        dto.setPunctuality(timeClock.getPunctuality());
        dto.setClockIn(timeClock.getClockIn());
        dto.setClockOut(timeClock.getClockOut());
        dto.setComment(timeClock.getComment());
        dto.setCompanyId(timeClock.getCompanyId());

        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing TimeClock",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete TimeClock", response = Response.class)
    })
    @RequestMapping(
            value = "/timeClock/deleteTimeClock/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteTimeClock(@PathVariable("id") final int id) {
        boolean isDeleted = timeClockService.deleteTimeClock(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }
}
