package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.DispatchDTO;
import com.ufril.medtran.dto.dispatch.JourneyLogDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.repository.dispatch.DispatchRepository;
import com.ufril.medtran.persistence.repository.dispatch.ShiftRepository;
import com.ufril.medtran.persistence.repository.dispatch.VehicleRepository;
import com.ufril.medtran.persistence.service.JourneyLogService;
import com.ufril.medtran.util.MapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController(value = "journeyLogResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "journeyLog")
public class JourneyLogResource {
    private static Logger logger = Logger.getLogger(JourneyLogResource.class);

    @Autowired
    private JourneyLogService journeyLogService;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DispatchRepository dispatchRepository;

    @ApiOperation(
            value = "Get All JourneyLog",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all JourneyLog", response = Response.class)
    })
    @RequestMapping(
            value = "/journeyLog/getAllJourneyLog",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllJourneyLog(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                              @RequestParam(required = false) Integer vehicleId,
                                              @RequestParam(defaultValue = "0") Integer pageNumber) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<JourneyLogs> data = journeyLogService.getAllJourneyLogs(startDate, endDate, vehicleId, pageable);

        List<JourneyLogDTO> list = new ArrayList<>();

        for(JourneyLogs log : data) {
            JourneyLogDTO dto = new JourneyLogDTO();
            dto.setId(log.getId());
            dto.setStartFrom(log.getStartFrom());
            dto.setDestination(log.getDestination());
            dto.setTransportBeginTime(log.getTransportBeginTime());
            dto.setAtDestination(log.getAtDestination());
            dto.setStartingOdometer(log.getStartingOdometer());
            dto.setEndOdometer(log.getEndOdometer());
            dto.setKmCovered(log.getKmCovered());
            dto.setBackInServiceTime(log.getBackInServiceTime());
            dto.setVehicleStatus(log.getVehicleStatus());

            if(log.getVehicle() != null) {
                dto.setVehicleId(log.getVehicle().getId());
                dto.setCallSign(log.getVehicle().getCallSign());
            }
            if(log.getDispatches() != null) {
                dto.setDispatchId(log.getDispatches().getId());
            }
            list.add(dto);
        }
        return new ResponseEntity<>(new Response(StatusType.OK, list), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a JourneyLog By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get JourneyLog", response = Response.class)
    })
    @RequestMapping(
            value = "/journeyLog/getJourneyLogById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getJourneyLogById(@PathVariable("id") final int id) {
        JourneyLogs log = journeyLogService.getJourneyLogById(id);

        JourneyLogDTO dto = new JourneyLogDTO();
        dto.setId(log.getId());
        dto.setStartFrom(log.getStartFrom());
        dto.setDestination(log.getDestination());
        dto.setTransportBeginTime(log.getTransportBeginTime());
        dto.setAtDestination(log.getAtDestination());
        dto.setStartingOdometer(log.getStartingOdometer());
        dto.setEndOdometer(log.getEndOdometer());
        dto.setKmCovered(log.getKmCovered());
        dto.setBackInServiceTime(log.getBackInServiceTime());
        dto.setVehicleStatus(log.getVehicleStatus());

        if(log.getVehicle() != null) {
            dto.setVehicleId(log.getVehicle().getId());
            dto.setCallSign(log.getVehicle().getCallSign());
        }
        if(log.getDispatches() != null) {
            dto.setDispatchId(log.getDispatches().getId());
        }
        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new JourneyLog",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new JourneyLog", response = Response.class)
    })
    @RequestMapping(
            value = "/journeyLog/createJourneyLog",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createJourneyLog(@RequestBody JourneyLogDTO journeyLogDTO) {
        JourneyLogs journeyLog = MapperUtils.mapDTOToJourneyLog(journeyLogDTO);

        Shifts shift = shiftRepository.findOne(journeyLogDTO.getShiftId());
        journeyLog.setShift(shift);

        Vehicles vehicle = vehicleRepository.findOne(journeyLogDTO.getVehicleId());
        journeyLog.setVehicle(vehicle);

        Dispatches dispatch = dispatchRepository.findOne(journeyLogDTO.getDispatchId());
        journeyLog.setDispatches(dispatch);

        journeyLog = journeyLogService.createJourneyLog(journeyLog);

        return new ResponseEntity<>(new Response(StatusType.OK, journeyLog), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing JourneyLog",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update JourneyLog", response = Response.class)
    })
    @RequestMapping(
            value = "/journeyLog/updateJourneyLog",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateJourneyLog(@RequestBody JourneyLogDTO journeyLogDTO) {
        JourneyLogs journeyLog = MapperUtils.mapDTOToJourneyLog(journeyLogDTO);

        Shifts shift = shiftRepository.findOne(journeyLogDTO.getShiftId());
        journeyLog.setShift(shift);

        Vehicles vehicle = vehicleRepository.findOne(journeyLogDTO.getVehicleId());
        journeyLog.setVehicle(vehicle);

        Dispatches dispatch = dispatchRepository.findOne(journeyLogDTO.getDispatchId());
        journeyLog.setDispatches(dispatch);

        journeyLog = journeyLogService.updateJourneyLog(journeyLog);

        return new ResponseEntity<>(new Response(StatusType.OK, journeyLog), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing JourneyLog",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete JourneyLog", response = Response.class)
    })
    @RequestMapping(
            value = "/payrollLog/deleteJourneyLog/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteJourneyLog(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, journeyLogService.deleteJourneyLog(id)), HttpStatus.OK);
    }
}
