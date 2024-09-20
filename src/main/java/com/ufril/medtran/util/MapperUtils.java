package com.ufril.medtran.util;

import com.ufril.medtran.dto.account.*;
import com.ufril.medtran.dto.common.ClaimDTO;
import com.ufril.medtran.dto.common.EquipmentChecklistDTO;
import com.ufril.medtran.dto.dispatch.*;
import com.ufril.medtran.dto.patient.PatientDTO;
import com.ufril.medtran.dto.patient.PatientDocumentDTO;
import com.ufril.medtran.persistence.domain.account.*;
import com.ufril.medtran.persistence.domain.common.Claim;
import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.domain.patient.PatientDocument;
import com.ufril.medtran.persistence.domain.patient.Patients;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moin
 *
 */
public class MapperUtils {

    /**
     * map a list object to a new list object
     * @param sources
     * @param destinationType
     * @param mapper
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T,S> List<T> mapAll(List<S> sources, Class<T> destinationType, ModelMapper mapper) {
        final List<T> destinations = new ArrayList<T>();
        for (S source : sources) {
            destinations.add(mapper.map(source, destinationType));
        }
        return destinations;
    }


    public static List<GetProfileDTO> mapAllUserToProfileDTO(List<User> sessions) {
        return sessions.stream().map(MapperUtils::mapUserToProfileDTO).collect(Collectors.toList());
    }

    public static GetProfileDTO mapUserToProfileDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        GetProfileDTO profile = modelMapper.map(user, GetProfileDTO.class);
		profile.setEnabled(user.isUserActive());

        List<String> roleList = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
                profile.setAdmin(true);
            }
            roleList.add(role.getName());
        }
        profile.setRoles(roleList);
        if (user.getCompany() != null) {
        	profile.setCompanyId(user.getCompany().getId());
		}
        return profile;

    }

	public static Company mapDTOToCompany(CompanyDTO companyDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Company company = modelMapper.map(companyDTO, Company.class);
		return company;

	}

    public static Dispatches mapDTOToDispatch(DispatchDTO dispatchDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dispatchDTO, Dispatches.class);
    }

    public static DispatchSchedules mapDTOToDispatchSchedule(DispatchDTO dispatchDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dispatchDTO, DispatchSchedules.class);
    }

    public static DispatchLogs mapDTOToDispatchLog(DispatchDTO dispatchDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dispatchDTO, DispatchLogs.class);
    }

    public static Shifts mapDTOToShift(ShiftDTO shiftDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shiftDTO, Shifts.class);
    }

	public static Patients mapDTOToPatient(PatientDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Patients.class);
	}

	public static PatientDocument mapDTOToDocument(PatientDocumentDTO documentDTO) {
		ModelMapper modelMapper = new ModelMapper();
		PatientDocument document = modelMapper.map(documentDTO, PatientDocument.class);
		return document;
	}

	public static PatientDocumentDTO mapDocumentToDTO(PatientDocument document) {
		ModelMapper modelMapper = new ModelMapper();
		PatientDocumentDTO documentDTO = modelMapper.map(document, PatientDocumentDTO.class);
		documentDTO.setPatientId(document.getPatient().getId());
		documentDTO.setDocumentType(document.getDocumentType().getName());
		return documentDTO;
	}

    public static RoleDTO mapRoleToDTO(Role role) {
        ModelMapper modelMapper = new ModelMapper();
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
        return roleDTO;
    }

    public static TimeClock mapDTOToTimeClock(TimeClockDTO timeClockDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(timeClockDTO, TimeClock.class);
    }

     public static FuelPurchaseLog mapDTOToFuelPurchaseLog(FuelPurchaseLogDTO fuelPurchaseLogDTO) {
         ModelMapper modelMapper = new ModelMapper();
         return modelMapper.map(fuelPurchaseLogDTO, FuelPurchaseLog.class);
    }
    public static EquipmentChecklist mapDTOToEquipmentChecklist(EquipmentChecklistDTO equipmentChecklistDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(equipmentChecklistDTO, EquipmentChecklist.class);
    }

    public static VehicleMaintenanceLog mapDTOToVehicleMaintenanceLog(VehicleMaintenanceLogDTO vehicleMaintenanceLogDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vehicleMaintenanceLogDTO, VehicleMaintenanceLog.class);
    }

    public static JourneyLogs mapDTOToJourneyLog(JourneyLogDTO journeyLogDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(journeyLogDTO, JourneyLogs.class);
    }

   /* public static FuelPurchaseLog updateFuelPurchaseLogFromDTO(FuelPurchaseLog existingFuelPurchaseLog, FuelPurchaseLogDTO fuelPurchaseLogDTO) {
        ModelMapper modelMapper = new ModelMapper();

        // Map DTO fields to existing entity fields
        modelMapper.map(fuelPurchaseLogDTO, existingFuelPurchaseLog);

        // Manually update any additional fields that are not present in the DTO
        // For example, the Vehicles and Employees associations
      //  existingFuelPurchaseLog.setVehicles(vehicleRepository.findOne(fuelPurchaseLogDTO.getVehicleId()));
    //    existingFuelPurchaseLog.setEmployee(employeeRepository.findOne(fuelPurchaseLogDTO.getEmployeeId()));

        return existingFuelPurchaseLog;
    }*/

    public static Employees mapDTOToEmployee(EmployeeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Employees.class);
    }

    public static Claim mapDTOToClaim(ClaimDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Claim.class);
    }

    public static Incident mapDTOToIncident(IncidentDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Incident.class);
    }
}
