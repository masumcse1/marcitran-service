package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.common.Location;
import com.ufril.medtran.persistence.domain.common.Station;
import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import com.ufril.medtran.persistence.domain.dispatch.ShiftCrewMembers;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ShiftService {

    List<Shifts> getAllShifts(Integer companyId, Integer Status, Pageable pageable);

    List<Shifts> getAllShiftsByDate(Integer companyId, Date fromDate, Date toDate);

    List<Shifts> getAllShiftsByDispatch(int dispatchId);

    Shifts getShiftById(int id);

    Shifts createShift(Shifts shift);

    Shifts updateShift(Shifts shift);

    boolean deleteShift(int id);

    void mapShiftCrewMembers(List<ShiftCrewMembers> shiftCrewMembers);

    void addJourneyLogs(JourneyLogs journeyLogs);

    List<Station> getAllStations();

    List<Location> getAllLocations();
}
