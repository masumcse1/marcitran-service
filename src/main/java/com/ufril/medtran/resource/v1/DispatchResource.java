package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.CallsByDispatcherDTO;
import com.ufril.medtran.dto.dispatch.CallsPerVehicleDTO;
import com.ufril.medtran.dto.dispatch.DispatchDTO;
import com.ufril.medtran.dto.dispatch.CallsPerDayNightDTO;
import com.ufril.medtran.dto.dispatch.PCRLogDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.Tag;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.domain.patient.Patients;
import com.ufril.medtran.persistence.service.*;
import com.ufril.medtran.util.MapperUtils;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController(value = "dispatchResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "dispatch")
public class DispatchResource {
    private static Logger logger = Logger.getLogger(DispatchResource.class);

    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private ServiceLevelService serviceLevelService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private TagService tagService;

    @Autowired
    private JavaMailSender mailSender;

    @ApiOperation(
            value = "Get All Dispatch",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/getAllDispatch",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllDispatch(@RequestParam Integer status,
                                            @RequestParam(required = false) Integer employeeId,
                                            @RequestParam(required = false) Integer vehicleId,
                                            @RequestParam(required = false) String patientName,
                                            @RequestParam(required = false) String dispatcher,
                                            @RequestParam(required = false) String shiftType,
                                            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        Pageable pageable = new PageRequest(pageNumber, 20);
        List<DispatchDTO> dispatches
                = dispatchService.getAllDispatch(status, employeeId, vehicleId, patientName, dispatcher, shiftType, pageable);

        return new ResponseEntity<>(new Response(StatusType.OK, dispatches), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All number of calls day and night shift",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get number of calls day and night shift", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/getCallsPerDayNightSplit",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getCallsPerDayNightSplit(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<CallsPerDayNightDTO> callsPerDayNightDTOS = dispatchService.getCallsPerDayNightSplit(startDate, endDate);
        return new ResponseEntity<>(new Response(StatusType.OK, callsPerDayNightDTOS), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All number of calls per vehicle",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get number of calls day and night shift", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/getCallsPerVehicle",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getCallsPerVehicle(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<CallsPerVehicleDTO> numCallByVehicle = dispatchService.countCallsPerVehicle(startDate, endDate);
        return new ResponseEntity<>(new Response(StatusType.OK, numCallByVehicle), HttpStatus.OK);

    }

    @ApiOperation(
            value = "Get All number of calls by dispatcher",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get number of calls day and night shift", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/getCallsByDispatcher",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getCallsByDispatcher(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<CallsByDispatcherDTO> callsByDispatcherDTO = dispatchService.getCallsByDispatcherCrewMember(startDate, endDate);

        return new ResponseEntity<>(new Response(StatusType.OK, callsByDispatcherDTO), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Dispatch By DispatchId",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/getDispatchById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getDispatchById(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, dispatchService.getDispatchById(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Dispatch",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/createDispatch",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createDispatch(@RequestBody DispatchDTO dispatchDTO) {
        Dispatches dispatch = MapperUtils.mapDTOToDispatch(dispatchDTO);

        Facilities origin = facilityService.getFacilitiesById(dispatchDTO.getOrigin());
        dispatch.setOrigin(origin);

        Facilities destination = facilityService.getFacilitiesById(dispatchDTO.getDestination());
        dispatch.setDestination(destination);

        ServiceLevel serviceLevel = serviceLevelService.getServiceLevelById(dispatchDTO.getServiceLevel());
        dispatch.setServiceLevel(serviceLevel);

        Patients patient = patientService.getPatientByID(dispatchDTO.getPatient());
        dispatch.setPatient(patient);

        Tag tag = tagService.getTagById(dispatchDTO.getTag());
        dispatch.setTag(tag);

        dispatch.setStatus(1);
        dispatch.setCreatedDate(new Date());
        dispatch = dispatchService.createDispatch(dispatch);


        DispatchSchedules dispatchSchedule = MapperUtils.mapDTOToDispatchSchedule(dispatchDTO);
        dispatchSchedule.setDispatch(dispatch);
        dispatchService.createDispatchSchedule(dispatchSchedule);


        DispatchLogs dispatchLog = MapperUtils.mapDTOToDispatchLog(dispatchDTO);
        dispatchLog.setDispatch(dispatch);

        Shifts shift = shiftService.getShiftById(dispatchDTO.getShift());
        dispatchLog.setShift(shift);

        dispatchService.createDispatchLog(dispatchLog);


        PCRLog pcrLog = new PCRLog();
        pcrLog.setDispatch(dispatch);
        dispatchService.createPCRLog(pcrLog);

        return new ResponseEntity<>(new Response(StatusType.OK, dispatch), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Dispatch",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update new Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/updateDispatch",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateDispatch(@RequestBody DispatchDTO dispatchDTO) {
        Dispatches dispatch = MapperUtils.mapDTOToDispatch(dispatchDTO);

        Facilities origin = facilityService.getFacilitiesById(dispatchDTO.getOrigin());
        dispatch.setOrigin(origin);

        Facilities destination = facilityService.getFacilitiesById(dispatchDTO.getDestination());
        dispatch.setDestination(destination);

        ServiceLevel serviceLevel = serviceLevelService.getServiceLevelById(dispatchDTO.getServiceLevel());
        dispatch.setServiceLevel(serviceLevel);

        Patients patient = patientService.getPatientByID(dispatchDTO.getPatient());
        dispatch.setPatient(patient);
        dispatch = dispatchService.updateDispatch(dispatch);

        DispatchSchedules dispatchSchedule = MapperUtils.mapDTOToDispatchSchedule(dispatchDTO);
        dispatchService.updateDispatchSchedule(dispatchSchedule);

        DispatchLogs dispatchLog = MapperUtils.mapDTOToDispatchLog(dispatchDTO);
        Shifts shift = shiftService.getShiftById(dispatchDTO.getShift());
        dispatchLog.setShift(shift);

        dispatchService.updateDispatchLog(dispatchLog);

        return new ResponseEntity<>(new Response(StatusType.OK, dispatch), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing Dispatch",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/deleteDispatch/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteDispatch(@PathVariable("id") final int id) {

            /*dispatchService.deleteDispatchLog(id);
            dispatchService.deleteDispatchSchedule(id);*/
        boolean flag = dispatchService.deleteDispatch(id);

        return new ResponseEntity<>(new Response(StatusType.OK, flag), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Get a PCR By DispatchId",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/getPCRByDispatchId/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getPCRByDispatchId(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, dispatchService.getPCRByDispatchId(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update PCR Log",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update PCR Log", response = Response.class)
    })
    @RequestMapping(
            value = "/dispatch/updatePCRLog",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updatePCRLog(@RequestBody PCRLogDTO pcrLogDTO) {

        dispatchService.updatePCRLog(pcrLogDTO);

        return new ResponseEntity<>(new Response(StatusType.OK, pcrLogDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/dispatch/emailPCR/{email}", method = RequestMethod.POST)
    public ResponseEntity<?> emailPCR(@PathVariable("email") String email, @RequestBody String pdfFile) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setSubject("Patient Care Report");
        messageHelper.setText("Download the attached file.");

        String pdfString = pdfFile.replace("data:application/pdf;filename=generated.pdf;base64,", "");
        byte[] decodedData = Base64.getDecoder().decode(pdfString);
        FileOutputStream outputStream = new FileOutputStream("PCRForm.pdf");
        outputStream.write(decodedData);
        outputStream.close();
        ByteArrayResource pdfAttachment = new ByteArrayResource(decodedData);

        messageHelper.addAttachment("PCRForm.pdf", pdfAttachment);
        messageHelper.setTo(email);

        mailSender.send(mimeMessage);

        return new ResponseEntity<>(new Response(StatusType.OK, "PCR sent via email"),
                HttpStatus.OK);
    }

}
