package com.xk.car.domain.model.enums;


/**
 * 車頭 板車
 * Hank create 8/11
 *
 */
public enum VehicleEnum {

    Head("車頭"),
    Trailer("板車");

    private String vehicleType;


    VehicleEnum(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public static VehicleEnum fromString(String status) {
        for (VehicleEnum e : VehicleEnum.values()) {
            if (e.name().equalsIgnoreCase(status) || e.vehicleType.equals(status)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid VehicleEnum action: " + status);
    }
}
