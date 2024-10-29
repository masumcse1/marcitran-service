package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.Affiliate;
import com.ufril.medtran.persistence.service.AffiliateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "affiliateResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "affiliate")
public class AffiliateResource {

    @Autowired
    private AffiliateService affiliateService;

    @ApiOperation(
            value = "Get All Affiliate By Company Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Affiliate", response = Response.class)
    })
    @RequestMapping(
            value = "/affiliate/getAllAffiliate/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllAffiliateByCompanyId(@PathVariable("companyId") int companyId) {
        List<Affiliate> affiliateList = affiliateService.getAllAffiliateByCompanyId(companyId);
        return new ResponseEntity<>(new Response(StatusType.OK, affiliateList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Affiliate By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Affiliate", response = Response.class)
    })
    @RequestMapping(
            value = "/affiliate/getAffiliateById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAffiliateById(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, affiliateService.getAffiliateById(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Affiliate",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Affiliate", response = Response.class)
    })
    @RequestMapping(
            value = "/affiliate/createAffiliate",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createAffiliate(Affiliate affiliate) {
        affiliate = affiliateService.createAffiliate(affiliate);
        return new ResponseEntity<>(new Response(StatusType.OK, affiliate), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Affiliate",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update Affiliate", response = Response.class)
    })
    @RequestMapping(
            value = "/affiliate/updateAffiliate",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateAffiliate(Affiliate affiliate) {
        affiliate = affiliateService.updateAffiliate(affiliate);
        return new ResponseEntity<>(new Response(StatusType.OK, affiliate), HttpStatus.OK);
    }
}
