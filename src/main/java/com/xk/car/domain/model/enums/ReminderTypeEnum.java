package com.xk.car.domain.model.enums;

public enum ReminderTypeEnum {

    MILEAGE("基於里程"),
    DATE("使用天數"),
    MANUAL("手動提醒");

    private String reminderType;

    ReminderTypeEnum(String reminderType){
        this.reminderType = reminderType;
    }

    public static ReminderTypeEnum fromString(String status) {
        for (ReminderTypeEnum e : ReminderTypeEnum.values()) {
            if (e.name().equalsIgnoreCase(status) || e.reminderType.equals(status)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid ReminderTypeEnum type: " + status);
    }
}
