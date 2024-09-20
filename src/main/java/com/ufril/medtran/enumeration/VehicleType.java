package com.ufril.medtran.enumeration;

/**
 * @author moin
 */
public enum VehicleType {

    CAR(4), VAN(6), BLACK_SUV(6), BLACK_CAR(4), MORE(9),
    WHEELCHAIR_VAN(1), BLS(1), ALS(1), ICU(1), PARATRANSIT(1),
    FLAT_BED(1), WHEEL_LIFT(1), HOOK_CHAIN(1), CAR_CARRIER(1),
    TOWING(1), GAS(1), FLAT_TIRE(1), BOOST(1);

    Integer value;

    VehicleType(Integer v) {
        this.value = v;
    }

    public Integer value() {
        return value;
    }

    public static VehicleType fromValue(Integer v) {
        for (VehicleType e : VehicleType.values()) {
            if (e.value.equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
