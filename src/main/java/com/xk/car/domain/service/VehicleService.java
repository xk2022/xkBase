package com.xk.car.domain.service;

import com.xk.car.application.model.VehicleCreateCmd;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.domain.model.bo.VihicleBo;

import java.util.UUID;

/**
 * 📌 `VehicleService`
 * - 提供 **車輛資訊（Order）** 的核心業務邏輯
 *
 * @author hank Created on 2025/08/17.
 */
public interface VehicleService {

     /**
      * 新增車輛資訊
      * @param cmd
      * @return
      */
     VihicleBo create(VehicleCreateCmd cmd);

     /**
      * 更新車輛資訊
      * @param uuid
      * @param cmd
      * @return
      */
     VihicleBo update(UUID uuid, VehicleCreateCmd cmd);

     /**
      * 刪除車輛資訊
      * @param uuid
      */
     void delete(UUID uuid);


     VihicleBo getVehicleByStatusAndLicensePlate(VehicleCreateCmd vehicleCreateCmd);
}
