package com.xk.car.application.mapper;

import com.xk.car.application.model.VehicleTripLogsCmd;
import com.xk.car.application.model.VehicleTripLogsRequest;
import com.xk.car.application.model.VehicleTripLogsResponse;
import com.xk.car.domain.model.bo.VehicleTripLogsBo;
import com.xk.car.domain.model.entity.VehicleTripLogsEntity;
import com.xk.car.infrastrcture.persistence.model.po.VehicleTripLogsPo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehicleTripLogsMapper {

    public VehicleTripLogsCmd toCreateCmd(VehicleTripLogsRequest request) {
        return XkBeanUtils.copyProperties(request ,VehicleTripLogsCmd::new);
    }

    public VehicleTripLogsEntity toEntity(VehicleTripLogsCmd cmd) {
        return XkBeanUtils.copyProperties(cmd,VehicleTripLogsEntity::new);
    }

    public VehicleTripLogsPo toPo(VehicleTripLogsEntity entity) {
        return XkBeanUtils.copyProperties(entity ,VehicleTripLogsPo::new );
    }

    public VehicleTripLogsBo toBo(VehicleTripLogsPo saved) {
        return XkBeanUtils.copyProperties(saved , VehicleTripLogsBo::new);
    }

    public VehicleTripLogsResponse toResponse(VehicleTripLogsBo result) {
        return XkBeanUtils.copyProperties(result , VehicleTripLogsResponse::new);
    }
}
