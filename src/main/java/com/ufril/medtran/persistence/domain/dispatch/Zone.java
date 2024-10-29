package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String northOf;
    private String southOf;
    private String eastOf;
    private String westOf;
    private String color;
    private String status;
    private int companyId;

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

    public String getNorthOf() {
        return northOf;
    }

    public void setNorthOf(String northOf) {
        this.northOf = northOf;
    }

    public String getSouthOf() {
        return southOf;
    }

    public void setSouthOf(String southOf) {
        this.southOf = southOf;
    }

    public String getEastOf() {
        return eastOf;
    }

    public void setEastOf(String eastOf) {
        this.eastOf = eastOf;
    }

    public String getWestOf() {
        return westOf;
    }

    public void setWestOf(String westOf) {
        this.westOf = westOf;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
