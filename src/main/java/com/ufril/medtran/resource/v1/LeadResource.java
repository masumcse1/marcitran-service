package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.Lead;
import com.ufril.medtran.persistence.service.LeadService;
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

import java.util.List;

@RestController(value = "leadResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "lead")
public class LeadResource {

    @Autowired
    private LeadService leadService;

    @ApiOperation(
            value = "Get All Lead",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Lead", response = Response.class)
    })
    @RequestMapping(
            value = "/lead/getAllLead/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllLead(@PathVariable("companyId") Integer companyId) {
        List<Lead> leadList = leadService.getAllLead(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, leadList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Lead By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Lead", response = Response.class)
    })
    @RequestMapping(
            value = "/lead/getLeadById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getLeadById(@PathVariable("id") final int id) {
        Lead lead = leadService.getLeadById(id);
        return new ResponseEntity<>(new Response(StatusType.OK, lead), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Lead",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Lead", response = Response.class)
    })
    @RequestMapping(
            value = "/lead/createLead",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createLead(@RequestBody Lead lead) {
        lead = leadService.createLead(lead);
        return new ResponseEntity<>(new Response(StatusType.OK, lead), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Lead",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update Lead", response = Response.class)
    })
    @RequestMapping(
            value = "/lead/updateLead",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateLead(@RequestBody Lead lead) {
        lead = leadService.updateLead(lead);
        return new ResponseEntity<>(new Response(StatusType.OK, lead), HttpStatus.OK);
    }
}
