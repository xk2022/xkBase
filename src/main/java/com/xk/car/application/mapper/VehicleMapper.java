package com.xk.car.application.mapper;


import com.xk.car.application.model.VehicleCreateCmd;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.application.model.VehicleResponse;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.entity.VehicleEntity;
import com.xk.car.infrastrcture.persistence.model.po.VehiclePo;
import com.xk.common.util.XkBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {


    public  VehicleCreateCmd toCreateVehicleCmd(VehicleRequest createDTO){
      return XkBeanUtils.copyProperties(createDTO ,VehicleCreateCmd::new);
    }

    public VehicleCreateCmd toUpdateCmd(VehicleRequest createDTO){
        return XkBeanUtils.copyProperties(createDTO , VehicleCreateCmd::new );
    }

    public VehicleResponse toResponseDto(VehicleBo result){
        return XkBeanUtils.copyProperties(result, VehicleResponse::new);
    }

    public VehicleEntity toEntity(VehicleCreateCmd cmd) {
        return XkBeanUtils.copyProperties(cmd,VehicleEntity::new);
    }

    public VehicleBo toBo(VehiclePo saved) {
        return XkBeanUtils.copyProperties(saved,VehicleBo::new);
    }

    public VehiclePo toPo(VehicleEntity vehicleEntity){
        return XkBeanUtils.copyProperties(vehicleEntity ,VehiclePo::new);

    }
}
