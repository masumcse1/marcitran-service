package com.ufril.medtran.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufril.medtran.persistence.domain.dispatch.VehicleGpsTrack;
import com.ufril.medtran.persistence.service.VehicleGpsTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Date;

@Controller
public class GpsTrackController {

    @Autowired
    private VehicleGpsTrackService vehicleGpsTrackService;

    @Autowired
    private ObjectMapper objectMapper;

    @MessageMapping("/track")
    public void track(@Payload String message) throws IOException {
        VehicleGpsTrack vehicleGpsTrack = objectMapper
                .readValue(message, VehicleGpsTrack.class);

        vehicleGpsTrack.setTrackAt(new Date());
        vehicleGpsTrackService.save(vehicleGpsTrack);
    }

}
