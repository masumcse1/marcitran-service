package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.account.EmployeeCertificateDTO;
import com.ufril.medtran.dto.account.EmployeeDTO;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.RoleType;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.helper.ResourceValidationHelper;
import com.ufril.medtran.persistence.domain.account.*;
import com.ufril.medtran.persistence.service.EmpCertService;
import com.ufril.medtran.persistence.service.EmployeeService;
import com.ufril.medtran.persistence.service.UserService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController(value = "employeeResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmpCertService empCertService;

    @Autowired
    private ResourceValidationHelper validationHelper;

    @RequestMapping(value = "employee/getAllEmployees/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmployees(@PathVariable("companyId") Integer companyId,
                                             @RequestParam boolean status,
                                             @RequestParam(required = false) String fullName,
                                             @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<Employees> employeesList = employeeService.getAllEmployees(
                companyId, status, fullName, pageable);

        List<EmployeeDTO> data = new ArrayList<>();

        for (Employees employee : employeesList) {
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId(employee.getId());
            employeeDTO.setFullName(employee.getFullName());
            employeeDTO.setPhone(employee.getPhone());
            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setEmergencyContactNo(employee.getEmergencyContactNo());
            employeeDTO.setGetEmergencyContactName(employee.getGetEmergencyContactName());
            employeeDTO.setCitizenship(employee.getCitizenship());
            employeeDTO.setFirstHired(employee.getFirstHired());
            employeeDTO.setStreetAddress(employee.getStreetAddress());
            employeeDTO.setState(employee.getState());
            employeeDTO.setCity(employee.getCity());
            employeeDTO.setZip(employee.getZip());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setActive(employee.isActive());
            employeeDTO.setCompanyId(employee.getCompanyId());

            User user = userService.getUserByEmployeeID(employee.getId());
            if (user != null) {
                employeeDTO.setUsername(user.getUsername());
                employeeDTO.setEmail(user.getEmail());

                Optional<Role> firstRole = user.getRoles().stream().findFirst();
                if (firstRole.isPresent()) {
                    employeeDTO.setRole(RoleType.valueOf(firstRole.get().getName()));
                }
            }
            data.add(employeeDTO);
        }

        return new ResponseEntity<>(new Response(StatusType.OK, data), HttpStatus.OK);
    }

    @RequestMapping(value = "employee/getEmployeeById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") final int id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employees employee = employeeService.getEmployeeById(id);

        employeeDTO.setId(employee.getId());
        employeeDTO.setFullName(employee.getFullName());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setEmergencyContactNo(employee.getEmergencyContactNo());
        employeeDTO.setGetEmergencyContactName(employee.getGetEmergencyContactName());
        employeeDTO.setCitizenship(employee.getCitizenship());
        employeeDTO.setFirstHired(employee.getFirstHired());
        employeeDTO.setStreetAddress(employee.getStreetAddress());
        employeeDTO.setState(employee.getState());
        employeeDTO.setCity(employee.getCity());
        employeeDTO.setZip(employee.getZip());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setCompanyId(employee.getCompanyId());

        User user = userService.getUserByEmployeeID(employee.getId());
        if (user != null) {
            employeeDTO.setUsername(user.getUsername());
            employeeDTO.setEmail(user.getEmail());

            Optional<Role> firstRole = user.getRoles().stream().findFirst();
            if (firstRole.isPresent()) {
                employeeDTO.setRole(RoleType.valueOf(firstRole.get().getName()));
            }
        }

        return new ResponseEntity<>(new Response(StatusType.OK, employeeDTO), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Employee",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "employee/createEmployee",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO, final Locale locale) {
        validationHelper.isEmailAlreadyUsed(employeeDTO.getEmail(), locale);

        Employees employee = MapperUtils.mapDTOToEmployee(employeeDTO);
        employee.setActive(true);
        employee = employeeService.createEmployee(employee);

/*
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setEmail(employeeDTO.getEmail());
        userDTO.setUsername(employeeDTO.getUsername());
        userDTO.setPassword(employeeDTO.getPassword());
        userDTO.setMatchingPassword(employeeDTO.getMatchingPassword());
        userDTO.setRole(employeeDTO.getRole());
        userDTO.setStatus("1");
        userDTO.setEmployeeId(employee.getId());
        userDTO.setCompanyId(employee.getCompanyId());

        userService.createUser(userDTO, null);
*/

        return new ResponseEntity<>(new Response(StatusType.OK, employee), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update Employee",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Dispatch", response = Response.class)
    })
    @RequestMapping(
            value = "employee/updateEmployee",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employees employee = MapperUtils.mapDTOToEmployee(employeeDTO);
        employee.setActive(true);
        employee = employeeService.updateEmployee(employee);

        User user = userService.getUserByEmployeeID(employeeDTO.getId());
        Optional<Role> firstRole = user.getRoles().stream().findFirst();

        if (firstRole.isPresent()) {
            userService.removeRoleFromUser(user, RoleType.valueOf(firstRole.get().getName()));
        }

        userService.assignRoleToUser(user, employeeDTO.getRole());

        return new ResponseEntity<>(new Response(StatusType.OK, employee), HttpStatus.OK);
    }

    @RequestMapping(value = "employee/deleteEmployee/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") final int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }

    @RequestMapping(value = "employee/getAllCertificates", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCertificates() {
        List<Certificates> certificates = empCertService.getAllCertificates();
        return new ResponseEntity<>(new Response(StatusType.OK, certificates), HttpStatus.OK);
    }

    @RequestMapping(value = "employee/getCertificatesByEmployeeId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCertificatesByEmployeeId(@PathVariable("id") final int id) {
        List<EmployeeCertificates> employeeCertificates = empCertService.getCertificatesByEmployeeId(id);
        return new ResponseEntity<>(new Response(StatusType.OK, employeeCertificates), HttpStatus.OK);
    }

    @RequestMapping(value = "employee/mapEmployeeCertificates", method = RequestMethod.POST)
    public ResponseEntity<?> mapEmployeeCertificates(@RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        empCertService.mapEmployeeCertificates(employeeCertificateDTO);
        return new ResponseEntity<>(new Response(StatusType.OK, employeeCertificateDTO), HttpStatus.OK);
    }

}
