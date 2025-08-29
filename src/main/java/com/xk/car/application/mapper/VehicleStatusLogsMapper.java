package com.xk.car.application.mapper;

import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleStatusLogsCmd;
import com.xk.car.application.model.VehicleStatusLogsResponse;
import com.xk.car.domain.model.bo.VehicleStatusLogsBo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehicleStatusLogsMapper {

    public VehicleStatusLogsCmd toTransCmd(VehicleRequest request) {
        return XkBeanUtils.copyProperties(request , VehicleStatusLogsCmd::new );
    }

    public VehicleStatusLogsResponse toResponseDto(VehicleStatusLogsBo result) {
        return XkBeanUtils.copyProperties(result,VehicleStatusLogsResponse::new);
    }
}
