package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.Facilities;
import com.ufril.medtran.persistence.service.FacilityService;
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

@RestController(value = "facilityResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "facility")
public class FacilityResource {
    private static Logger logger = Logger.getLogger(FacilityResource.class);

    @Autowired
    private FacilityService facilityService;

    @ApiOperation(
            value = "Get All Facility",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Facility", response = Response.class)
    })
    @RequestMapping(
            value = "/facility/getAllFacilities",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllFacility() {
        List<Facilities> facilityList = facilityService.getAllFacilities();

        return new ResponseEntity<>(new Response(StatusType.OK, facilityList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Facility By Name",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Facility", response = Response.class)
    })
    @RequestMapping(
            value = "/facility/getFacilityByName/{name}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getFacilityByName(@PathVariable("name") String name) {
        Facilities facility = facilityService.getFacilityByName(name);
        return new ResponseEntity<>(new Response(StatusType.OK, facility), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Facility By FacilityId",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Facility", response = Response.class)
    })
    @RequestMapping(
            value = "/facility/getFacilitiesById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getFacilityById(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, facilityService.getFacilitiesById(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Facility",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Facility", response = Response.class)
    })
    @RequestMapping(
            value = "/facility/createFacilities",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createFacility(@RequestBody Facilities facilities) {
        facilities = facilityService.createFacilities(facilities);

        return new ResponseEntity<>(new Response(StatusType.OK, facilities), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Facility",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update new Facility", response = Response.class)
    })
    @RequestMapping(
            value = "/Facility/updateFacilities",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateFacility(Facilities facilities) {
        facilities = facilityService.updateFacilities(facilities);

        return new ResponseEntity<>(new Response(StatusType.OK, facilities), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing Facility",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Facility", response = Response.class)
    })
    @RequestMapping(
            value = "/Facility/deleteFacilities/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteFacility(@PathVariable("id") final int id) {

        boolean flag = facilityService.deleteFacilities(id);

        return new ResponseEntity<>(new Response(StatusType.OK, flag), HttpStatus.OK);
    }
}
