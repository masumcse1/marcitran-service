package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.VehicleMaintenanceLogDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.dispatch.VehicleMaintenanceLog;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.repository.dispatch.VehicleRepository;
import com.ufril.medtran.persistence.service.VehicleMaintenanceLogService;
import com.ufril.medtran.util.MapperUtils;
import com.ufril.medtran.util.Utils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController(value = "vehicleMaintenanceLogResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "vehicleMaintenanceLog")
public class VehicleMaintenanceLogResource {

    @Autowired
    private VehicleMaintenanceLogService vehicleMaintenanceLogService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(value = "/vehicleMaintenanceLogs/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable("companyId") Integer companyId,
                                    @RequestParam("startDate")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                    @RequestParam("endDate")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                    @RequestParam(value = "vehicleId", required = false) Integer vehicleId,
                                    @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);
        List<VehicleMaintenanceLog> data = vehicleMaintenanceLogService
                .getAllVehicleMaintenanceLog(companyId, startDate, endDate, vehicleId, pageable);

        List<VehicleMaintenanceLogDTO> list = new ArrayList<>();

        for (VehicleMaintenanceLog log : data) {
            VehicleMaintenanceLogDTO dto = new VehicleMaintenanceLogDTO();
            dto.setId(log.getId());
            dto.setActive(log.isActive());
            dto.setDate(log.getDate());
            dto.setOdometer(log.getOdometer());
            dto.setLocation(log.getLocation());
            dto.setCost(log.getCost());
            dto.setNotes(log.getNotes());
            dto.setCompanyId(log.getCompanyId());

            if (log.getVehicles() != null) {
                dto.setVehicleId(log.getVehicles().getId());
                dto.setCallSign(log.getVehicles().getCallSign());
            }
            list.add(dto);
        }

        return new ResponseEntity<>(new Response(StatusType.OK, list), HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicleMaintenanceLogById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") final int id) {
        VehicleMaintenanceLog log = vehicleMaintenanceLogService.getVehicleMaintenanceLogById(id);

        VehicleMaintenanceLogDTO dto = new VehicleMaintenanceLogDTO();
        dto.setId(log.getId());
        dto.setActive(log.isActive());
        dto.setDate(log.getDate());
        dto.setOdometer(log.getOdometer());
        dto.setLocation(log.getLocation());
        dto.setCost(log.getCost());
        dto.setNotes(log.getNotes());
        dto.setDownloadUri(log.getDownloadUri());
        dto.setCompanyId(log.getCompanyId());

        if (log.getVehicles() != null) {
            dto.setVehicleId(log.getVehicles().getId());
            dto.setCallSign(log.getVehicles().getCallSign());
        }
        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/vehicleMaintenanceLog",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> create(@RequestBody VehicleMaintenanceLogDTO vehicleMaintenanceLogDTO) throws Exception {
        VehicleMaintenanceLog vehicleMaintenanceLog = MapperUtils.mapDTOToVehicleMaintenanceLog(vehicleMaintenanceLogDTO);

        Vehicles vehicles = vehicleRepository.findOne(vehicleMaintenanceLogDTO.getVehicleId());
        vehicleMaintenanceLog.setVehicles(vehicles);

        vehicleMaintenanceLog.setMaintenanceType(null);
        vehicleMaintenanceLog.setRepairType(null);

        vehicleMaintenanceLog = vehicleMaintenanceLogService.createVehicleMaintenanceLog(vehicleMaintenanceLog);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicleMaintenanceLog), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/vehicleMaintenanceLog",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> update(@RequestBody VehicleMaintenanceLogDTO vehicleMaintenanceLogDTO) {
        VehicleMaintenanceLog vehicleMaintenanceLog = MapperUtils.mapDTOToVehicleMaintenanceLog(vehicleMaintenanceLogDTO);

        Vehicles vehicles = vehicleRepository.findOne(vehicleMaintenanceLogDTO.getVehicleId());
        vehicleMaintenanceLog.setVehicles(vehicles);

        vehicleMaintenanceLog.setMaintenanceType(null);
        vehicleMaintenanceLog.setRepairType(null);

        vehicleMaintenanceLog = vehicleMaintenanceLogService.updateVehicleMaintenanceLog(vehicleMaintenanceLog);
        return new ResponseEntity<>(new Response(StatusType.OK, vehicleMaintenanceLog), HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicleMaintenanceLog/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") final int id) {
        boolean isDeleted = vehicleMaintenanceLogService.deleteVehicleMaintenanceLog(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicleMaintenanceLog/downloadFileImage/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadFile(@PathVariable("id") int id) {
        VehicleMaintenanceLog vehicleMaintenanceLog = vehicleMaintenanceLogService.getVehicleMaintenanceLogById(id);
        Path filePath = Paths.get(Utils.getFilePath() + "/" + vehicleMaintenanceLog.getStoredFileName());
        Resource resource = new FileSystemResource(filePath.toFile());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + vehicleMaintenanceLog.getOriginalFileName() + "\"")
                .body(resource);
    }
}
