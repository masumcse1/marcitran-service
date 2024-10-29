package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.dto.dispatch.CallsByDispatcherDTO;
import com.ufril.medtran.dto.dispatch.CallsPerDayNightDTO;
import com.ufril.medtran.dto.dispatch.CallsPerVehicleDTO;
import com.ufril.medtran.dto.dispatch.DispatchDTO;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatches, Integer> {

    List<Dispatches> findAllByCompanyId(Integer companyId);

    @Query("SELECT new " +
            "com.ufril.medtran.dto.dispatch.DispatchDTO(" +
            "l.dispatch.id, l.dispatch.caller, l.dispatch.phone, " +
            "l.dispatch.origin, dest, p, l.dispatch.serviceLevel, l.shift, l.dispatch.createdBy, l.dispatch.createdDate) " +
            "FROM DispatchLogs l " +
            "LEFT JOIN l.dispatch.Destination dest " +
            "LEFT JOIN l.dispatch.patient p " +
            "WHERE l.companyId = :companyId " +
            "AND (:employeeId IS NULL OR l.shift.id IN (SELECT m.shiftID.id FROM ShiftCrewMembers m WHERE m.employeeID.id = :employeeId)) " +
            "AND (:vehicleId IS NULL OR l.shift.vehicle.id = :vehicleId)" +
            "AND (:patientName IS NULL OR p.firstName LIKE CONCAT('%', :patientName, '%'))" +
            "AND (:createdBy IS NULL OR l.dispatch.createdBy LIKE CONCAT('%', :createdBy, '%'))" +
            "AND (:shiftType IS NULL OR l.shift.shiftType = :shiftType)" +
            "AND l.dispatch.status = :status " +
            "ORDER BY l.dispatch.id desc")
    List<DispatchDTO> getAllDispatchByCompanyId(@Param("companyId") Integer companyId,
                                                @Param("status") Integer status,
                                                @Param("employeeId") Integer employeeId,
                                                @Param("vehicleId") Integer vehicleId,
                                                @Param("patientName") String patientName,
                                                @Param("createdBy") String createdBy,
                                                @Param("shiftType") String shiftType,
                                                Pageable pageable);

    @Query(value = "SELECT new com.ufril.medtran.dto.dispatch.CallsPerDayNightDTO(" +
            "CASE WHEN HOUR(d.createdDate) >= 7 AND HOUR(d.createdDate) <= 19 THEN 'Day' ELSE 'Night' END, " +
            "COUNT(d)) " +
            "FROM Dispatches d " +
            "WHERE d.companyId = :companyId " +
            "AND d.createdDate BETWEEN :startDate AND :endDate " +
            "GROUP BY CASE WHEN HOUR(d.createdDate) >= 7 AND HOUR(d.createdDate) <= 19 THEN 'Day' ELSE 'Night' END")
    List<CallsPerDayNightDTO> getCallsPerDayNightSplitByCompanyId(@Param("companyId") Integer companyId,
                                                                  @Param("startDate") Date startDate,
                                                                  @Param("endDate") Date endDate);

    @Query(value = "SELECT NEW com.ufril.medtran.dto.dispatch.CallsPerVehicleDTO(v.callSign, COUNT(d)) " +
            "FROM DispatchLogs d " +
            "JOIN d.shift.vehicle v " +
            "WHERE d.companyId = :companyId " +
            "AND d.dispatch.createdDate BETWEEN :startDate AND :endDate " +
            "GROUP BY v.callSign")
    List<CallsPerVehicleDTO> countCallsPerVehicleByCompanyId(@Param("companyId") Integer companyId,
                                                             @Param("startDate") Date startDate,
                                                             @Param("endDate") Date endDate);

    @Query(value = "SELECT new com.ufril.medtran.dto.dispatch.CallsByDispatcherDTO(d.createdBy, COUNT(d)) " +
            "FROM Dispatches d " +
            "WHERE d.companyId = :companyId " +
            "AND d.createdDate BETWEEN :startDate AND :endDate " +
            "GROUP BY d.createdBy")
    List<CallsByDispatcherDTO> getCallsByDispatcherCrewMemberAndCompanyId(@Param("companyId") Integer companyId,
                                                                          @Param("startDate") Date startDate,
                                                                          @Param("endDate") Date endDate);

}
