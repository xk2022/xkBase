package com.xk.car.application.converter;

import com.xk.car.application.model.VehiclePairingsCmd;
import com.xk.car.application.model.VehiclePairingsRequest;
import com.xk.car.application.model.VehiclePairingsResponse;
import com.xk.car.domain.model.bo.VehiclePairingsBo;
import com.xk.car.domain.model.entity.VehiclePairingsEntity;
import com.xk.car.infrastrcture.persistence.model.po.VehiclePairingsPo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehiclePairingsConverter {

    public VehiclePairingsCmd toCreateVehiclePairingsCmd(VehiclePairingsRequest request) {
        return XkBeanUtils.copyProperties(request , VehiclePairingsCmd::new);
    }

    public VehiclePairingsEntity toEntity(VehiclePairingsCmd cmd) {
        return XkBeanUtils.copyProperties(cmd ,VehiclePairingsEntity::new);
    }

    public VehiclePairingsPo toPo(VehiclePairingsEntity entity) {
        return XkBeanUtils.copyProperties(entity ,VehiclePairingsPo::new);
    }

    public VehiclePairingsBo toBo(VehiclePairingsPo saved) {
        return XkBeanUtils.copyProperties(saved , VehiclePairingsBo::new);
    }

    public VehiclePairingsResponse toResponse(VehiclePairingsBo result) {
        return XkBeanUtils.copyProperties(result,VehiclePairingsResponse::new);
    }

}
