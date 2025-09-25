package com.xk.common.util;

import org.springframework.stereotype.Component;

@Component
public class VehicleStatusConverterUtils {

    public String getVehicleStatus(String status){
        String statusStr ="";

        switch (status){
            case "空閒":
                statusStr ="IDLE";
                break;
            case "忙碌":
                statusStr ="BUSY";
                break;
            case "維修中":
                statusStr ="MAINTENANCE";
                break;
        }

        return statusStr ;
    }


    public String getMaintenanceType(String MaintenanceType){
        String statusStr ="";

        switch (MaintenanceType){
            case "定期維修":
                statusStr ="REGULAR";
                break;
            case "異常維修":
                statusStr ="INCIDENT";
                break;
            case "使用者申報":
                statusStr ="MANUAL";
                break;
        }

        return statusStr ;
    }
}
