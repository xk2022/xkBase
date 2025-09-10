package com.xk.car.application.converter;


import com.xk.car.application.model.VehicleMaintenanceCreateCmd;
import com.xk.car.application.model.VehicleMaintenanceRequest;

import com.xk.car.application.model.VehicleMaintenanceResponse;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;
import com.xk.car.domain.model.entity.VehicleMaintenanceEntity;
import com.xk.car.infrastrcture.persistence.model.po.VehicleMaintenancePo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehicleMaintenanceConverter {


    public VehicleMaintenanceCreateCmd toCreateVehicleMaintenanceCmd(VehicleMaintenanceRequest createDTO){
        return XkBeanUtils.copyProperties(createDTO ,VehicleMaintenanceCreateCmd::new);
    }

    public VehicleMaintenanceEntity toEntity(VehicleMaintenanceCreateCmd cmd) {
        return XkBeanUtils.copyProperties(cmd,VehicleMaintenanceEntity::new);
    }

    public VehicleMaintenancePo toPo(VehicleMaintenanceEntity entity) {
        return XkBeanUtils.copyProperties(entity,VehicleMaintenancePo::new);
    }

    public VehicleMaintenanceBo toBo(VehicleMaintenancePo saved) {
        return XkBeanUtils.copyProperties(saved , VehicleMaintenanceBo::new );
    }

    public VehicleMaintenanceResponse toResponseDto(VehicleMaintenanceBo result) {
        return XkBeanUtils.copyProperties(result , VehicleMaintenanceResponse::new);
    }

    public VehicleMaintenancePo BOtoPO(VehicleMaintenanceBo vehicleMaintenanceBo) {
        return XkBeanUtils.copyProperties(vehicleMaintenanceBo , VehicleMaintenancePo::new);
    }
}
