package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
