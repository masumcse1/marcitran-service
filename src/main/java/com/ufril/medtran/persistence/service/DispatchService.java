package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.dispatch.*;
import com.ufril.medtran.persistence.domain.dispatch.DispatchLogs;
import com.ufril.medtran.persistence.domain.dispatch.DispatchSchedules;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import com.ufril.medtran.persistence.domain.dispatch.PCRLog;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface DispatchService {

    List<DispatchDTO> getAllDispatchByCompanyId(Integer companyId,
                                                Integer status,
                                                Integer employeeId,
                                                Integer vehicleId,
                                                String patientName,
                                                String dispatcher,
                                                String shiftType,
                                                Pageable pageable);

    List<CallsPerDayNightDTO> getCallsPerDayNightSplitByCompanyId(Integer companyId,
                                                                  Date startDate,
                                                                  Date endDate);

    List<CallsPerVehicleDTO> countCallsPerVehicleByCompanyId(Integer companyId,
                                                             Date startDate,
                                                             Date endDate);

    List<CallsByDispatcherDTO> getCallsByDispatcherCrewMemberAndCompanyId(Integer companyId,
                                                                         Date startDate,
                                                                         Date endDate);

    DispatchDTO getDispatchById(int id);

    Dispatches getDispatchByDate(Date date);

    Dispatches createDispatch(Dispatches dispatch);

    Dispatches updateDispatch(Dispatches dispatch);

    boolean deleteDispatch(int id);

    DispatchSchedules createDispatchSchedule(DispatchSchedules dispatchSchedule);

    DispatchSchedules updateDispatchSchedule(DispatchSchedules dispatchSchedule);

    boolean deleteDispatchSchedule(int id);

    DispatchLogs createDispatchLog(DispatchLogs dispatchLog);

    DispatchLogs updateDispatchLog(DispatchLogs dispatchLog);

    boolean deleteDispatchLog(int id);

    DispatchLogs assignShift(int dispatcherId, int shiftId);

    PCRLog getPCRByDispatchId(int id);

    void createPCRLog(PCRLog pcrLog);

    void updatePCRLog(PCRLogDTO pcrLogDTO);
    //List<DispatchDTO> countDispatchesByDayNightSplit(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
