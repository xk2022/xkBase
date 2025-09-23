package com.xk.common.util;

import org.springframework.stereotype.Component;

@Component
public class VehicleStatusConverterUtils {

    public String getVehicleStatus(String status){
        String statusStr ="";

        switch (status){
            case "空閒":
                statusStr ="0";
                break;
            case "忙碌":
                statusStr ="1";
                break;
            case "維修中":
                statusStr ="2";
                break;
        }

        return statusStr ;
    }


    public String getMaintenanceType(String MaintenanceType){
        String statusStr ="";

        switch (MaintenanceType){
            case "定期維修":
                statusStr ="0";
                break;
            case "異常維修":
                statusStr ="1";
                break;
            case "使用者申報":
                statusStr ="2";
                break;
        }

        return statusStr ;
    }
}
