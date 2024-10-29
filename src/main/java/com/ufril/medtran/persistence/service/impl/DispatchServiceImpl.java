package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.dispatch.*;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.repository.dispatch.*;
import com.ufril.medtran.persistence.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchRepository dispatchRepository;

    @Autowired
    private DispatchSchedulesRepository dispatchSchedulesRepository;

    @Autowired
    private DispatchLogsRepository dispatchLogsRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private PCRLogRepository pcrLogRepository;

    @Override
    public List<DispatchDTO> getAllDispatchByCompanyId(Integer companyId,
                                                       Integer status,
                                                       Integer employeeId,
                                                       Integer vehicleId,
                                                       String patientName,
                                                       String dispatcher,
                                                       String shiftType,
                                                       Pageable pageable) {

        return dispatchRepository.getAllDispatchByCompanyId(companyId,
                status, employeeId, vehicleId, patientName, dispatcher,
                shiftType, pageable);
    }

    @Override
    public List<CallsPerDayNightDTO> getCallsPerDayNightSplitByCompanyId(Integer companyId,
                                                                         Date startDate,
                                                                         Date endDate) {

        return dispatchRepository.getCallsPerDayNightSplitByCompanyId(companyId,
                startDate, endDate);
    }

    @Override
    public List<CallsPerVehicleDTO> countCallsPerVehicleByCompanyId(Integer companyId,
                                                                    Date startDate,
                                                                    Date endDate) {

        return dispatchRepository.countCallsPerVehicleByCompanyId(companyId,
                startDate, endDate);
    }

    @Override
    public List<CallsByDispatcherDTO> getCallsByDispatcherCrewMemberAndCompanyId(Integer companyId,
                                                                                Date startDate,
                                                                                Date endDate) {

        return dispatchRepository.getCallsByDispatcherCrewMemberAndCompanyId(companyId,
                startDate, endDate);
    }

    @Override
    public DispatchDTO getDispatchById(int id) {
        DispatchDTO dispatchDTO = new DispatchDTO();
        Dispatches dispatch = dispatchRepository.findOne(id);
        DispatchSchedules schedule = dispatchSchedulesRepository.findByDispatch(dispatch);
        DispatchLogs dispatchLogs = dispatchLogsRepository.findByDispatch(dispatch);

        dispatchDTO.setId(dispatch.getId());
        dispatchDTO.setCaller(dispatch.getCaller());
        dispatchDTO.setPhone(dispatch.getPhone());

        if (dispatch.getOrigin() != null) {
            dispatchDTO.setOrigin(dispatch.getOrigin().getId());
            dispatchDTO.setOriginAddress(dispatch.getOrigin().getName());
        }

        if (dispatch.getDestination() != null) {
            dispatchDTO.setDestination(dispatch.getDestination().getId());
            dispatchDTO.setDestinationAddress(dispatch.getDestination().getName());
        }

        if (dispatch.getPatient() != null)
            dispatchDTO.setPatient(dispatch.getPatient().getId());

        if (dispatchLogs.getShift() != null)
            dispatchDTO.setShift(dispatchLogs.getShift().getId());

        if (dispatch.getServiceLevel() != null)
            dispatchDTO.setServiceLevel(dispatch.getServiceLevel().getId());

        dispatchDTO.setPriceQuote(dispatch.getPriceQuote());
        dispatchDTO.setPaymentMode(dispatch.getPaymentMode());
        dispatchDTO.setStatus(dispatch.getStatus());

        dispatchDTO.setActivateThisRunTime(schedule.getActivateThisRunTime());
        dispatchDTO.setBeAtOriginTime(schedule.getBeAtOriginTime());
        dispatchDTO.setBeAtDestinationTime(schedule.getBeAtDestinationTime());
        dispatchDTO.setFrequency(schedule.getFrequency());
        dispatchDTO.setThrough(schedule.getThrough());
        dispatchDTO.setTripType(schedule.getTripType());
        dispatchDTO.setReturnTripActivatedAt(schedule.getReturnTripActivatedAt());
        dispatchDTO.setPickBackupAt(schedule.getPickBackupAt());

        dispatchDTO.setComplaint(dispatch.getComplaint());
        dispatchDTO.setWarnings(dispatch.getWarnings());
        dispatchDTO.setPriorAuth(dispatch.isPriorAuth());
        dispatchDTO.setPriority(dispatch.getPriority());
        dispatchDTO.setCommentsToCrew(dispatch.getCommentsToCrew());
        dispatchDTO.setBillingNotes(dispatch.getBillingNotes());

        dispatchDTO.setCreatedBy(dispatch.getCreatedBy());
        dispatchDTO.setCreatedDate(dispatch.getCreatedDate());

        return dispatchDTO;
    }

    @Override
    public Dispatches getDispatchByDate(Date date) {
        return null;
    }

    @Override
    public Dispatches createDispatch(Dispatches dispatch) {
        return dispatchRepository.save(dispatch);
    }

    @Override
    public Dispatches updateDispatch(Dispatches dispatch) {
        return dispatchRepository.save(dispatch);
    }

    @Override
    public boolean deleteDispatch(int id) {
        Dispatches dispatches = dispatchRepository.findOne(id);
        dispatches.setStatus(0);
        dispatchRepository.save(dispatches);
        return true;
    }

    @Override
    public DispatchSchedules createDispatchSchedule(DispatchSchedules dispatchSchedule) {
        return dispatchSchedulesRepository.save(dispatchSchedule);
    }

    @Override
    public DispatchSchedules updateDispatchSchedule(DispatchSchedules dispatchSchedule) {
        return dispatchSchedulesRepository.save(dispatchSchedule);
    }

    @Override
    public boolean deleteDispatchSchedule(int id) {
        dispatchSchedulesRepository.delete(id);
        return true;
    }

    @Override
    public DispatchLogs createDispatchLog(DispatchLogs dispatchLog) {
        return dispatchLogsRepository.save(dispatchLog);
    }

    @Override
    public DispatchLogs updateDispatchLog(DispatchLogs dispatchLog) {
        return dispatchLogsRepository.save(dispatchLog);
    }

    @Override
    public boolean deleteDispatchLog(int id) {
        dispatchLogsRepository.delete(id);
        return true;
    }

    @Override
    public DispatchLogs assignShift(int dispatcherId, int shiftId) {
        DispatchLogs dispatchLog = dispatchLogsRepository.findOne(dispatcherId);
        Shifts shift = shiftRepository.findOne(shiftId);
        dispatchLog.setShift(shift);

        dispatchLogsRepository.save(dispatchLog);

        return dispatchLog;
    }

    @Override
    public PCRLog getPCRByDispatchId(int id) {
        return pcrLogRepository.findByDispatchId(id);
    }

    @Override
    public void createPCRLog(PCRLog pcrLog) {
        pcrLogRepository.save(pcrLog);
    }

    @Override
    public void updatePCRLog(PCRLogDTO pcrLogDTO) {
        PCRLog pcrLog = pcrLogRepository.findByDispatchId(pcrLogDTO.getDispatchId());

        if (pcrLog.getSubmittedTime() == null)
            pcrLog.setSubmittedTime(new Date());

        if (pcrLog.getCreatedBy() == null)
            pcrLog.setCreatedBy(pcrLogDTO.getCreatedBy());

        if (pcrLogDTO.getSignature() != null) {
            String imageString = pcrLogDTO.getSignature().replace("data:image/png;base64,", "");
            byte[] signatureBytes = Base64.getDecoder().decode(imageString);
            pcrLog.setSignature(signatureBytes);
        }

        if (pcrLogDTO.getAssessment() != null && !pcrLogDTO.getAssessment().trim().isEmpty())
            pcrLog.setAssessment(pcrLogDTO.getAssessment());

        if (pcrLogDTO.getEcgRecord() != null && !pcrLogDTO.getEcgRecord().trim().isEmpty())
            pcrLog.setEcgRecord(pcrLogDTO.getEcgRecord());

        if (pcrLogDTO.getIntervention() != null && !pcrLogDTO.getIntervention().trim().isEmpty())
            pcrLog.setIntervention(pcrLogDTO.getIntervention());

        if (pcrLogDTO.getIcActivity() != null && !pcrLogDTO.getIcActivity().trim().isEmpty())
            pcrLog.setIcActivity(pcrLogDTO.getIcActivity());

        if (pcrLogDTO.getLabTest() != null && !pcrLogDTO.getLabTest().trim().isEmpty())
            pcrLog.setLabTest(pcrLogDTO.getLabTest());

        if (pcrLogDTO.getMedication() != null && !pcrLogDTO.getMedication().trim().isEmpty())
            pcrLog.setMedication(pcrLogDTO.getMedication());

        if (pcrLogDTO.getMiReport() != null && !pcrLogDTO.getMiReport().trim().isEmpty())
            pcrLog.setMiReport(pcrLogDTO.getMiReport());

        if (pcrLogDTO.getProcedure() != null && !pcrLogDTO.getProcedure().trim().isEmpty())
            pcrLog.setProcedure(pcrLogDTO.getProcedure());

        if (pcrLogDTO.getVital() != null && !pcrLogDTO.getVital().trim().isEmpty())
            pcrLog.setVital(pcrLogDTO.getVital());

        if (pcrLogDTO.getDetails() != null && !pcrLogDTO.getDetails().trim().isEmpty())
            pcrLog.setDetails(pcrLogDTO.getDetails());

        pcrLogRepository.save(pcrLog);
    }
}
