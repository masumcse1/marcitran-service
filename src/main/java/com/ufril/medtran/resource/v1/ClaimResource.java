package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.ClaimDTO;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.Claim;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.repository.dispatch.DispatchRepository;
import com.ufril.medtran.persistence.repository.dispatch.VehicleRepository;
import com.ufril.medtran.persistence.service.ClaimService;
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

import java.util.List;

@RestController(value = "claimResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "claim")
public class ClaimResource {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DispatchRepository dispatchRepository;

    @ApiOperation(
            value = "Get All Claim By Company Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Claim", response = Response.class)
    })
    @RequestMapping(
            value = "/claim/getAllClaim/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllClaim(@PathVariable("companyId") int companyId,
                                         @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<ClaimDTO> claimList = claimService.getAllClaimByCompanyId(companyId, pageable);
        return new ResponseEntity<>(new Response(StatusType.OK, claimList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Claim By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Claim", response = Response.class)
    })
    @RequestMapping(
            value = "/claim/getClaimById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getClaimById(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, claimService.getClaimById(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Claim",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Claim", response = Response.class)
    })
    @RequestMapping(
            value = "/claim/createClaim",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createClaim(@RequestBody ClaimDTO claimDTO) {
        Claim claim = MapperUtils.mapDTOToClaim(claimDTO);

        Vehicles vehicles = vehicleRepository.findOne(claimDTO.getVehicleId());
        claim.setVehicles(vehicles);

        Dispatches dispatch = dispatchRepository.findOne(claimDTO.getDispatchId());
        claim.setDispatches(dispatch);

        claim = claimService.createClaim(claim);

        return new ResponseEntity<>(new Response(StatusType.OK, claim), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Claim",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update Claim", response = Response.class)
    })
    @RequestMapping(
            value = "/claim/updateClaim",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateClaim(@RequestBody ClaimDTO claimDTO) {
        Claim claim = MapperUtils.mapDTOToClaim(claimDTO);

        Vehicles vehicles = vehicleRepository.findOne(claimDTO.getVehicleId());
        claim.setVehicles(vehicles);

        claim = claimService.updateClaim(claim);

        return new ResponseEntity<>(new Response(StatusType.OK, claim), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete an existing Claim",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to delete Claim", response = Response.class)
    })
    @RequestMapping(
            value = "/payrollLog/deleteClaim/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deleteClaim(@PathVariable("id") final int id) {
        boolean isDeleted = claimService.deleteClaim(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }
}
