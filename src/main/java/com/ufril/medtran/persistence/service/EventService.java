package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.common.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvent(int companyId);

    Event getEventById(int id);

    Event createEvent(Event event);

    Event updateEvent(Event event);
}
