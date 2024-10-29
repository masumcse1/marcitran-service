package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.patient.PatientDTO;
import com.ufril.medtran.dto.patient.PatientDocumentDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.patient.PatientDocument;
import com.ufril.medtran.persistence.domain.patient.Patients;
import com.ufril.medtran.persistence.service.PatientService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "patientResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "patient")
public class PatientResource {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/createPatient", method = RequestMethod.POST)
    public ResponseEntity<?> createNewPatient(@RequestBody PatientDTO dto) {
        Patients patient = patientService.createNewPatient(dto);
        return new ResponseEntity<>(new Response(StatusType.OK, patient), HttpStatus.OK);
    }

    @RequestMapping(value = "/editPatient", method = RequestMethod.POST)
    public ResponseEntity<?> editPatient(@RequestBody PatientDTO dto) {
        Patients patient = patientService.getPatientByID(dto.getId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setMiddleName(dto.getMiddleName());
        patient.setSuffix(dto.getSuffix());
        patient.setState(dto.getState());
        patient.setCity(dto.getCity());
        patient.setZip(dto.getZip());
        patient.setCountry(dto.getCountry());
        patient.setStatus(dto.getStatus());
        patient.setBarcode(dto.getBarcode());
        patient.setAge(dto.getAge());
        patient.setEmail(dto.getEmail());
        patient.setHomePhone(dto.getHomePhone());
        patient.setWorkPhone(dto.getWorkPhone());
        patient.setOtherData(dto.getOtherData());

        patient = patientService.savePatient(patient);
        return new ResponseEntity<>(new Response(StatusType.OK, patient), HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/getPatientById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientById(@PathVariable("id") final int id) {
        Patients patient = patientService.getPatientByID(id);
        return new ResponseEntity<>(new Response(StatusType.OK, patient), HttpStatus.OK);
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
            value = "/getPatientByName/{companyId}/{name}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getPatientByName(@PathVariable("companyId") Integer companyId,
                                              @PathVariable("name") String name) {

        Patients patient = patientService.getPatientByName(companyId, name);
        return new ResponseEntity<>(new Response(StatusType.OK, patient), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllPatients/{companyId}/{status}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPatients(@PathVariable("companyId") Integer companyId,
                                            @PathVariable("status") final Integer status,
                                            @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<Patients> patientsList = patientService.getAllPatients(companyId, status, pageable);
        return new ResponseEntity<>(new Response(StatusType.OK, patientsList), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletePatient", method = RequestMethod.POST)
    public ResponseEntity<?> deletePatient(@RequestBody PatientDTO dto) {
        patientService.deletePatient(dto.getId());
        return new ResponseEntity<>(new Response(StatusType.OK, true), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadPatientDocument", method = RequestMethod.POST)
    public ResponseEntity<?> uploadPatientDocument(@RequestBody PatientDocumentDTO dto) {
        PatientDocument document = patientService.saveDocument(dto);
        dto = MapperUtils.mapDocumentToDTO(document);
        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDocumentsByPatient", method = RequestMethod.POST)
    public ResponseEntity<?> getDocumentsByPatient(@RequestBody PatientDocumentDTO dto) {
        List<PatientDocumentDTO> documents = patientService.getDocumentsByPatient(dto);
        return new ResponseEntity<>(new Response(StatusType.OK, documents), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteDocument", method = RequestMethod.POST)
    public ResponseEntity<?> deleteDocument(@RequestBody PatientDocumentDTO dto) {
        patientService.deleteDocument(dto.getId());
        return new ResponseEntity<>(new Response(StatusType.OK, true), HttpStatus.OK);
    }

}
