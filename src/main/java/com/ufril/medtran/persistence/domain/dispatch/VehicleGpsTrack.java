package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_gps_track")
public class VehicleGpsTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date trackAt;

    private String deviceId;
    private String latitude;
    private String longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTrackAt() {
        return trackAt;
    }

    public void setTrackAt(Date trackAt) {
        this.trackAt = trackAt;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
