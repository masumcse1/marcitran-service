package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.account.CompanyDTO;
import com.ufril.medtran.dto.account.CreateUserDTO;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.helper.ResourceValidationHelper;
import com.ufril.medtran.persistence.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController(value = "companyResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "company")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ResourceValidationHelper validationHelper;

    public static class CompanyAndUserDTO {

        private CompanyDTO company;
        private CreateUserDTO user;

        public CompanyDTO getCompany() {
            return company;
        }

        public void setCompany(CompanyDTO company) {
            this.company = company;
        }

        public CreateUserDTO getUser() {
            return user;
        }

        public void setUser(CreateUserDTO user) {
            this.user = user;
        }
    }


    @ApiOperation(
            value = "Create a new company",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new company", response = Response.class)
    })
    @RequestMapping(
            value = "/createCompany",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createCompany(@RequestBody CompanyAndUserDTO dto, Locale locale) {
        validationHelper.isEmailAlreadyUsed(dto.getUser().getEmail(), locale);
        Map<String, Object> response = companyService.createCompanyWithAdminUser(dto.getCompany(), dto.getUser());
        return new ResponseEntity<>(new Response(StatusType.OK, response), HttpStatus.OK);
    }
}
