package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.dto.dispatch.FuelPurchaseLogDTO;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.repository.account.EmployeeRepository;
import com.ufril.medtran.persistence.repository.dispatch.VehicleRepository;
import com.ufril.medtran.persistence.service.FuelPurchaseLogService;
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


@RestController(value = "fuelPurchaseLogResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "fuelPurchaseLog")
public class FuelPurchaseLogResource {

    @Autowired
    private FuelPurchaseLogService fuelPurchaseLogService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/fuelPurchaseLog/{companyId}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getAllFuelPurchaseLog(@PathVariable("companyId") Integer companyId,
                                                   @RequestParam("startDate")
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                   @RequestParam("endDate")
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                   @RequestParam(value = "vehicleId", required = false) Integer vehicleId,
                                                   @RequestParam(defaultValue = "0") Integer pageNumber) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNumber, 10, sort);

        List<FuelPurchaseLog> data = fuelPurchaseLogService.getAllFuelPurchaseLogs(
                companyId, startDate, endDate, vehicleId, pageable);

        List<FuelPurchaseLogDTO> list = new ArrayList<>();

        for (FuelPurchaseLog log : data) {
            FuelPurchaseLogDTO dto = new FuelPurchaseLogDTO();
            dto.setId(log.getId());
            dto.setPurchaseDate(log.getPurchaseDate());
            dto.setOdometer(log.getOdometer());
            dto.setFuelLevel(log.getFuelLevel());
            dto.setTankType(log.getTankType());
            dto.setLocation(log.getLocation());
            dto.setCost(log.getCost());
            dto.setGallons(log.getGallons());
            dto.setNotes(log.getNotes());
            dto.setAttendant(log.getAttendant());
            dto.setCompanyId(log.getCompanyId());

            if (log.getVehicles() != null) {
                dto.setVehicleId(log.getVehicles().getId());
                dto.setCallSign(log.getVehicles().getCallSign());
            }

            if (log.getEmployee() != null) {
                dto.setEmployeeId(log.getEmployee().getId());
                dto.setEmployeeName(log.getEmployee().getFullName());
            }

            list.add(dto);
        }

        return new ResponseEntity<>(new Response(StatusType.OK, list), HttpStatus.OK);
    }

    @RequestMapping(value = "/fuelPurchaseLog/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getFuelPurchaseLogById(@PathVariable("id") final int id) {
        FuelPurchaseLog log = fuelPurchaseLogService.getFuelPurchaseLogById(id);

        FuelPurchaseLogDTO dto = new FuelPurchaseLogDTO();
        dto.setId(log.getId());
        dto.setPurchaseDate(log.getPurchaseDate());
        dto.setOdometer(log.getOdometer());
        dto.setFuelLevel(log.getFuelLevel());
        dto.setTankType(log.getTankType());
        dto.setLocation(log.getLocation());
        dto.setCost(log.getCost());
        dto.setGallons(log.getGallons());
        dto.setNotes(log.getNotes());
        dto.setAttendant(log.getAttendant());
        dto.setDownloadUri(log.getDownloadUri());
        dto.setCompanyId(log.getCompanyId());

        if (log.getVehicles() != null) {
            dto.setVehicleId(log.getVehicles().getId());
            dto.setCallSign(log.getVehicles().getCallSign());
        }

        if (log.getEmployee() != null) {
            dto.setEmployeeId(log.getEmployee().getId());
            dto.setEmployeeName(log.getEmployee().getFullName());
        }

        return new ResponseEntity<>(new Response(StatusType.OK, dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/fuelPurchaseLog",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createFuelPurchaseLog(@RequestBody FuelPurchaseLogDTO dto) throws Exception {
        FuelPurchaseLog fuelPurchaseLog = MapperUtils.mapDTOToFuelPurchaseLog(dto);

        Vehicles vehicles = vehicleRepository.findOne(dto.getVehicleId());
        fuelPurchaseLog.setVehicles(vehicles);

        Employees employees = employeeRepository.findOne(dto.getEmployeeId());
        fuelPurchaseLog.setEmployee(employees);

        fuelPurchaseLog = fuelPurchaseLogService.createFuelPurchaseLog(fuelPurchaseLog);

        return new ResponseEntity<>(new Response(StatusType.OK, fuelPurchaseLog), HttpStatus.OK);
    }

    @RequestMapping(value = "/fuelPurchaseLog",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateFuelPurchaseLog(@RequestBody FuelPurchaseLogDTO dto) {
        FuelPurchaseLog fuelPurchaseLog = MapperUtils.mapDTOToFuelPurchaseLog(dto);
        fuelPurchaseLog = fuelPurchaseLogService.updateFuelPurchaseLog(fuelPurchaseLog);
        return new ResponseEntity<>(new Response(StatusType.OK, fuelPurchaseLog), HttpStatus.OK);
    }

    @RequestMapping(value = "/fuelPurchaseLog/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFuelPurchaseLog(@PathVariable("id") final int id) {
        Boolean isDeleted = fuelPurchaseLogService.deleteFuelPurchaseLog(id);
        return new ResponseEntity<>(new Response(StatusType.OK, isDeleted), HttpStatus.OK);
    }

    @RequestMapping(value = "/fuelPurchaseLog/downloadFileImage/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> downloadFile(@PathVariable("id") int id) {
        FuelPurchaseLog fuelPurchaseLog = fuelPurchaseLogService.getFuelPurchaseLogById(id);
        Path filePath = Paths.get(Utils.getFilePath() + "/" + fuelPurchaseLog.getStoredFileName());
        Resource resource = new FileSystemResource(filePath.toFile());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fuelPurchaseLog.getOriginalFileName() + "\"")
                .body(resource);
    }
}
