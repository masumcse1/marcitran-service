package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.ServiceLevel;
import com.ufril.medtran.persistence.service.ServiceLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController(value = "serviceLevelResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "serviceLevel")
public class ServiceLevelResource {
    private static Logger logger = Logger.getLogger(ServiceLevelResource.class);

    @Autowired
    private ServiceLevelService serviceLevelService;

    @ApiOperation(
            value = "Get All ServiceLevel",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all ServiceLevel", response = Response.class)
    })
    @RequestMapping(
            value = "/serviceLevel/getAllServiceLevel",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllServiceLevel() {
        List<ServiceLevel> serviceLevelList = serviceLevelService.getAllServiceLevels();

        return new ResponseEntity<>(new Response(StatusType.OK, serviceLevelList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a ServiceLevel By ServiceLevelId",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete ServiceLevel", response = Response.class)
    })
    @RequestMapping(
            value = "/serviceLevel/getServiceLevelById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getServiceLevelById(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, serviceLevelService.getServiceLevelById(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new ServiceLevel",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new ServiceLevel", response = Response.class)
    })
    @RequestMapping(
            value = "/serviceLevel/createServiceLevel",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createServiceLevel(@RequestBody ServiceLevel serviceLevel) {
        serviceLevel = serviceLevelService.createServiceLevel(serviceLevel);

        return new ResponseEntity<>(new Response(StatusType.OK, serviceLevel), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing ServiceLevel",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update new ServiceLevel", response = Response.class)
    })
    @RequestMapping(
            value = "/ServiceLevel/updateServiceLevel",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateServiceLevel(@RequestBody ServiceLevel serviceLevel) {
        serviceLevel = serviceLevelService.updateServiceLevel(serviceLevel);

        return new ResponseEntity<>(new Response(StatusType.OK, serviceLevel), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing ServiceLevel",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete ServiceLevel", response = Response.class)
    })
    @RequestMapping(
            value = "/ServiceLevel/deleteServiceLevel/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteServiceLevel(@PathVariable("id") final int id) {

        boolean flag = serviceLevelService.deleteServiceLevel(id);

        return new ResponseEntity<>(new Response(StatusType.OK, flag), HttpStatus.OK);
    }
}
