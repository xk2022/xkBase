package com.xk.car.domain.model.enums;

public enum VehicleStatusEnum {

    IDLE("空閒"),
    BUSY("忙碌"),
    MAINTENANCE("維修中");

    private String vehicleStatus;

    VehicleStatusEnum(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public static VehicleStatusEnum fromString(String status) {
        for (VehicleStatusEnum e : VehicleStatusEnum.values()) {
            if (e.name().equalsIgnoreCase(status) || e.vehicleStatus.equals(status)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid VehicleStatusEnum action: " + status);
    }
}
