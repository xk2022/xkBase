package com.xk.car.application.mapper;

import com.xk.car.application.model.VehiclePartsUsageCmd;
import com.xk.car.application.model.VehiclePartsUsageRequest;
import com.xk.car.application.model.VehiclePartsUsageResponse;
import com.xk.car.domain.model.bo.VehiclePartsUsageBo;
import com.xk.car.domain.model.entity.VehiclePartsUsageEntity;
import com.xk.car.infrastrcture.persistence.model.po.VehiclePartsUsagePo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehiclePartsUsageMapper {
    public VehiclePartsUsageCmd toCreateVehiclePartsUsageCmd(VehiclePartsUsageRequest request) {
        return XkBeanUtils.copyProperties(request ,VehiclePartsUsageCmd::new);
    }

    public VehiclePartsUsageEntity toEntity(VehiclePartsUsageCmd cmd) {
        return XkBeanUtils.copyProperties(cmd ,VehiclePartsUsageEntity::new);
    }

    public VehiclePartsUsagePo toPo(VehiclePartsUsageEntity entity) {
        return XkBeanUtils.copyProperties(entity ,VehiclePartsUsagePo::new);
    }

    public VehiclePartsUsageBo toBo(VehiclePartsUsagePo saved) {
        return XkBeanUtils.copyProperties(saved,VehiclePartsUsageBo::new);
    }

    public VehiclePartsUsageResponse toResponseDto(VehiclePartsUsageBo result) {
        return XkBeanUtils.copyProperties(result ,VehiclePartsUsageResponse::new);
    }

    public VehiclePartsUsageCmd toUpdateCmd(VehiclePartsUsageRequest request) {
        return XkBeanUtils.copyProperties(request ,VehiclePartsUsageCmd::new);
    }
}
