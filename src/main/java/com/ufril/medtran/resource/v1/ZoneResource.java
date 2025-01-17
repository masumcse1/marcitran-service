package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.Zone;
import com.ufril.medtran.persistence.service.ZoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "zoneResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "zone")
public class ZoneResource {

    @Autowired
    private ZoneService zoneService;

    @ApiOperation(
            value = "Get All Zone",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Zone", response = Response.class)
    })
    @RequestMapping(
            value = "/zone/getAllZone/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllZone(@PathVariable("companyId") Integer companyId) {
        List<Zone> zoneList = zoneService.getAllZone(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, zoneList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Zone By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Zone", response = Response.class)
    })
    @RequestMapping(
            value = "/zone/getZoneById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getZoneById(@PathVariable("id") final int id) {
        Zone zone = zoneService.getZoneById(id);
        return new ResponseEntity<>(new Response(StatusType.OK, zone), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Zone",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Zone", response = Response.class)
    })
    @RequestMapping(
            value = "/zone/createZone",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createZone(@RequestBody Zone zone) {
        zone = zoneService.createZone(zone);
        return new ResponseEntity<>(new Response(StatusType.OK, zone), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Zone",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update Zone", response = Response.class)
    })
    @RequestMapping(
            value = "/zone/updateZone",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateZone(@RequestBody Zone zone) {
        zone = zoneService.updateZone(zone);
        return new ResponseEntity<>(new Response(StatusType.OK, zone), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing Zone",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Zone", response = Response.class)
    })
    @RequestMapping(
            value = "/zone/deleteZone/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteZone(@PathVariable("id") final int id) {
        boolean isDeleted = zoneService.deleteZone(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }
}
