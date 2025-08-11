package com.xk.car.domain.model.enums;

public enum MaintenanceTypeEnum {

    REGULAR("定期維修"),

    INCIDENT("異常維修"),

    MANUAL("使用者申報");

    private String maintenanceType;

    MaintenanceTypeEnum(String maintenanceType){
        this.maintenanceType = maintenanceType;
    }

    public static MaintenanceTypeEnum fromString(String status) {
        for (MaintenanceTypeEnum e : MaintenanceTypeEnum.values()) {
            if (e.name().equalsIgnoreCase(status) || e.maintenanceType.equals(status)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid MaintenanceTypeEnum type: " + status);
    }
}
