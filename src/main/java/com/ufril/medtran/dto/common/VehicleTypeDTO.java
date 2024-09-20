package com.ufril.medtran.dto.common;

import com.ufril.medtran.enumeration.VehicleCategory;
import com.ufril.medtran.enumeration.VehicleType;

/**
 * @author moin
 */
public class VehicleTypeDTO {

    private VehicleCategory category;
    private String name;
    private VehicleType value;
    private int passenger;

    public VehicleTypeDTO(VehicleCategory category, String name, VehicleType value, int passenger) {
        this.category = category;
        this.name = name;
        this.value = value;
        this.passenger = passenger;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getValue() {
        return value;
    }

    public void setValue(VehicleType value) {
        this.value = value;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleTypeDTO{");
        sb.append("category='").append(category).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", passenger=").append(passenger);
        sb.append('}');
        return sb.toString();
    }
}
