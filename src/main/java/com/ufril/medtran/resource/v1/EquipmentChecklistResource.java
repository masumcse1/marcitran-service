package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.EquipmentChecklistDTO;
import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import com.ufril.medtran.persistence.domain.common.EquipmentType;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.repository.common.EquipmentTypeRepository;
import com.ufril.medtran.persistence.repository.dispatch.ShiftRepository;
import com.ufril.medtran.persistence.service.EquipmentChecklistService;
import com.ufril.medtran.util.MapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController(value = "equipmentChecklistResourceV1")
@RequestMapping(value = {"/v1/", "/oauth2/v1/"})
@Api(value = "equipmentChecklist")
public class EquipmentChecklistResource {

    @Autowired
    private EquipmentChecklistService checklistService;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EquipmentTypeRepository equipmentTypeRepository;

    @ApiOperation(
            value = "Get All EquipmentChecklist",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all EquipmentChecklist", response = Response.class)
    })
    @RequestMapping(
            value = "/checklist/getAll/{companyId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllEquipmentChecklist(@PathVariable("companyId") Integer companyId,
                                                      @RequestParam Integer shiftId,
                                                      @RequestParam("checkDate")
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkDate,
                                                      @RequestParam Integer checkType) {

        List<EquipmentChecklistDTO> equipmentChecklistList = checklistService.getAll(
                companyId, shiftId, checkDate, checkType);

        if (equipmentChecklistList == null || equipmentChecklistList.isEmpty()) {
            equipmentChecklistList = new ArrayList<>();

            List<EquipmentType> equipmentTypes = equipmentTypeRepository.findAllByCheckType(checkType);

            for (EquipmentType type : equipmentTypes) {
                EquipmentChecklistDTO dto = new EquipmentChecklistDTO();
                dto.setEquipmentTypeId(type.getId());
                dto.setEquipmentTypeName(type.getName());
                equipmentChecklistList.add(dto);
            }
        }

        return new ResponseEntity<>(new Response(StatusType.OK, equipmentChecklistList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Save EquipmentChecklist",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new EquipmentChecklist", response = Response.class)
    })
    @RequestMapping(
            value = "/checklist/save",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> save(@RequestBody List<EquipmentChecklistDTO> dtoList) {
        List<EquipmentChecklist> list = new ArrayList<>();

        for (EquipmentChecklistDTO dto : dtoList) {
            EquipmentChecklist equipmentChecklist = MapperUtils.mapDTOToEquipmentChecklist(dto);

            Shifts shift = shiftRepository.findOne(dto.getShiftId());
            equipmentChecklist.setShift(shift);

            EquipmentType equipmentType = equipmentTypeRepository.findOne(dto.getEquipmentTypeId());
            equipmentChecklist.setEquipmentType(equipmentType);

            list.add(equipmentChecklist);
        }

        checklistService.save(list);

        return new ResponseEntity<>(new Response(StatusType.OK, dtoList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get All Equipment Types",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all EquipmentChecklist", response = Response.class)
    })
    @RequestMapping(
            value = "/checklist/getEquipmentTypes",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getEquipmentTypes() {
        List<EquipmentType> types = equipmentTypeRepository.findAll();
        return new ResponseEntity<>(new Response(StatusType.OK, types), HttpStatus.OK);
    }
}
