package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.common.Event;
import com.ufril.medtran.persistence.repository.common.EventRepository;
import com.ufril.medtran.persistence.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvent(int companyId) {
        return eventRepository.findAllByCompanyId(companyId);
    }

    @Override
    public Event getEventById(int id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }
}
