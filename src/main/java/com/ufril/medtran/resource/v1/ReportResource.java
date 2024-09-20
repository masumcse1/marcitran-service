package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.report.PortfolioDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController(value = "reportResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "report")
public class ReportResource {
    private static Logger logger = Logger.getLogger(ReportResource.class);

    @Autowired
    private ReportService reportService;

    @ApiOperation(
            value = "Get Portfolio Growth",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Portfolio Growth", response = Response.class)
    })
    @RequestMapping(
            value = "/report/getPortfolioGrowth",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getPortfolioGrowth() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int pastYear = currentYear - 1;

        List<PortfolioDTO> portfolioDTO = reportService.getPortfolioGrowth(currentYear, pastYear);

        return new ResponseEntity<>(new Response(StatusType.OK, portfolioDTO), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get Customer Retention",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Customer Retention", response = Response.class)
    })
    @RequestMapping(
            value = "/report/getCustomerRetention",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getCustomerRetention() {
        int retentionRatio = reportService.getCustomerRetention();

        return new ResponseEntity<>(new Response(StatusType.OK, retentionRatio), HttpStatus.OK);
    }
}
