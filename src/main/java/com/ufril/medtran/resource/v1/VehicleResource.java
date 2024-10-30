package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.service.VehicleService;
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

import java.util.List;

@RestController(value = "vehicleResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "vehicle")
public class VehicleResource {

    @Autowired
    private VehicleService vehicleService;

    @ApiOperation(
            value = "Get All Vehicle",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Vehicle", response = Response.class)
    })
    @RequestMapping(
            value = "/vehicle/getAllVehicles/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllVehicle(@PathVariable("companyId") Integer companyId,
                                           @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<Vehicles> vehicleList = vehicleService.getAllVehicles(companyId, pageable);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicleList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Vehicle By VehicleId",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Vehicle", response = Response.class)
    })
    @RequestMapping(
            value = "/vehicle/getVehicleById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getVehicleById(@PathVariable("id") final int id) {
        Vehicles vehicles = vehicleService.getVehicleById(id);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicles), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Vehicle",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Vehicle", response = Response.class)
    })
    @RequestMapping(
            value = "/vehicle/createVehicle",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createVehicle(@RequestBody Vehicles vehicles) {
        vehicles = vehicleService.createVehicle(vehicles);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicles), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Vehicle",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update new Vehicle", response = Response.class)
    })
    @RequestMapping(
            value = "/vehicle/updateVehicle",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicles vehicles) {
        vehicles = vehicleService.updateVehicle(vehicles);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicles), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing Vehicle",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Vehicle", response = Response.class)
    })
    @RequestMapping(
            value = "/vehicle/deleteVehicle/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") final int id) {
        boolean flag = vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(new Response(StatusType.OK, flag), HttpStatus.OK);
    }
}
