package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.IncidentDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.dispatch.Incident;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.repository.account.EmployeeRepository;
import com.ufril.medtran.persistence.repository.dispatch.VehicleRepository;
import com.ufril.medtran.persistence.service.IncidentService;
import com.ufril.medtran.util.MapperUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController(value = "incidentResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "incident")
public class IncidentResource {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IncidentService incidentService;

    @RequestMapping(
            value = "/incident/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllIncident(@PathVariable("companyId") Integer companyId,
                                            @RequestParam("startDate")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                            @RequestParam("endDate")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                            @RequestParam(value = "vehicleId", required = false) Integer vehicleId,
                                            @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);

        List<Incident> data = incidentService.getAllIncidents(
                companyId, startDate, endDate, vehicleId, pageable);

        List<IncidentDTO> list = new ArrayList<>();
        for (Incident incident : data) {
            IncidentDTO dto = new IncidentDTO();
            dto.setId(incident.getId());
            dto.setIncidentTime(incident.getIncidentTime());
            dto.setSummary(incident.getSummary());
            dto.setCause(incident.getCause());
            dto.setNotes(incident.getNotes());

            if (incident.getVehicle() != null) {
                dto.setVehicleId(incident.getVehicle().getId());
                dto.setCallSign(incident.getVehicle().getCallSign());
            }
            if (incident.getPrimaryActor() != null) {
                dto.setPrimaryActor(incident.getPrimaryActor().getId());
                dto.setPrimaryActorName(incident.getPrimaryActor().getFullName());
            }
            if (incident.getSecondaryActor() != null) {
                dto.setSecondaryActor(incident.getSecondaryActor().getId());
                dto.setSecondaryActorName(incident.getSecondaryActor().getFullName());
            }

            list.add(dto);
        }

        return new ResponseEntity<>(new Response(StatusType.OK, list), HttpStatus.OK);
    }

    @RequestMapping(value = "/incident/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getIncidentById(@PathVariable("id") final int id) {
        Incident incident = incidentService.getIncidentById(id);

        IncidentDTO dto = new IncidentDTO();
        dto.setId(incident.getId());
        dto.setIncidentTime(incident.getIncidentTime());
        dto.setSummary(incident.getSummary());
        dto.setCause(incident.getCause());
        dto.setNotes(incident.getNotes());

        if (incident.getVehicle() != null) {
            dto.setVehicleId(incident.getVehicle().getId());
            dto.setCallSign(incident.getVehicle().getCallSign());
        }
        if (incident.getPrimaryActor() != null) {
            dto.setPrimaryActor(incident.getPrimaryActor().getId());
            dto.setPrimaryActorName(incident.getPrimaryActor().getFullName());
        }
        if (incident.getSecondaryActor() != null) {
            dto.setSecondaryActor(incident.getSecondaryActor().getId());
            dto.setSecondaryActorName(incident.getSecondaryActor().getFullName());
        }

        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/incident",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createIncident(@RequestBody IncidentDTO dto) throws Exception {
        Incident incident = MapperUtils.mapDTOToIncident(dto);

        Vehicles vehicles = vehicleRepository.findOne(dto.getVehicleId());
        incident.setVehicle(vehicles);

        Employees primaryActor = employeeRepository.findOne(dto.getPrimaryActor());
        incident.setPrimaryActor(primaryActor);

        Employees secondaryActor = employeeRepository.findOne(dto.getSecondaryActor());
        incident.setSecondaryActor(secondaryActor);

        incident = incidentService.createIncident(incident);

        return new ResponseEntity<>(new Response(StatusType.OK, incident), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/incident",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateIncident(Incident incident) {
        incident = incidentService.updateIncident(incident);
        return new ResponseEntity<>(new Response(StatusType.OK, incident), HttpStatus.OK);
    }

    @RequestMapping(value = "/incident/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteIncident(@PathVariable("id") final int id) {
        boolean isDeleted = incidentService.deleteIncident(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }
}
