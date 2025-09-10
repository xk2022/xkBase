package com.xk.car.application.converter;

import com.xk.car.application.model.VehicleStatusLogsCmd;
import com.xk.car.application.model.VehicleStatusLogsRequest;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import com.xk.car.domain.model.entity.VehicleStatusLogsEntity;
import com.xk.car.infrastrcture.persistence.model.po.VehicleStatusLogsPo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehicleStatusLogsConverter {

    public VehicleStatusLogsCmd toTransCmd(VehicleStatusLogsRequest request) {
        return XkBeanUtils.copyProperties(request , VehicleStatusLogsCmd::new );
    }

    public VehicleStatusLogsResponse toResponseDto(VehicleStatusLogsBo result) {
        return XkBeanUtils.copyProperties(result,VehicleStatusLogsResponse::new);
    }

    public VehicleStatusLogsEntity toEntity(VehicleStatusLogsCmd cmd) {
        return XkBeanUtils.copyProperties(cmd, VehicleStatusLogsEntity::new);
    }

    public VehicleStatusLogsPo toPo(VehicleStatusLogsEntity entity) {
        return XkBeanUtils.copyProperties(entity , VehicleStatusLogsPo::new);
    }

    public VehicleStatusLogsBo toBo(VehicleStatusLogsPo saved) {
        return XkBeanUtils.copyProperties(saved , VehicleStatusLogsBo::new);
    }
}
