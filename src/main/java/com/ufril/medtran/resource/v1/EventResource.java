package com.ufril.medtran.resource.v1;

import com.ufril.medtran.dto.common.Response;
import com.ufril.medtran.enumeration.StatusType;
import com.ufril.medtran.persistence.domain.common.Event;
import com.ufril.medtran.persistence.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "eventResourceV1")
@RequestMapping(value = { "/v1/", "/oauth2/v1/" })
@Api(value = "event")
public class EventResource {
    private static Logger logger = Logger.getLogger(EventResource.class);

    @Autowired
    private EventService eventService;

    @ApiOperation(
            value = "Get All Event",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get all Event", response = Response.class)
    })
    @RequestMapping(
            value = "/event/getAllEvent",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllEvent() {
        List<Event> eventList = eventService.getAllEvent();

        return new ResponseEntity<>(new Response(StatusType.OK, eventList), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get a Event By Id",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to get Event", response = Response.class)
    })
    @RequestMapping(
            value = "/event/getEventById/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getEventById(@PathVariable("id") final int id) {
        return new ResponseEntity<>(new Response(StatusType.OK, eventService.getEventById(id)), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create a new Event",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to create new Event", response = Response.class)
    })
    @RequestMapping(
            value = "/event/createEvent",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        event = eventService.createEvent(event);

        return new ResponseEntity<>(new Response(StatusType.OK, event), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update an existing Event",
            response = Response.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Response.class),
            @ApiResponse(code = 404, message = "Unable to update Event", response = Response.class)
    })
    @RequestMapping(
            value = "/event/updateEvent",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateEvent(@RequestBody Event event) {
        event = eventService.updateEvent(event);

        return new ResponseEntity<>(new Response(StatusType.OK, event), HttpStatus.OK);
    }
}
