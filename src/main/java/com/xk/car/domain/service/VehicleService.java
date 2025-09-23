package com.xk.car.domain.service;

import com.xk.car.application.model.VehicleCreateCmd;
import com.xk.car.application.model.VehicleRequest;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;

import java.util.List;
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
     VehicleBo create(VehicleCreateCmd cmd);

     /**
      * 更新車輛資訊
      * @param uuid
      * @param cmd
      * @return
      */
     VehicleBo update(UUID uuid, VehicleCreateCmd cmd);

     /**
      * 刪除車輛資訊
      * @param uuid
      */
     void delete(UUID uuid);


     VehicleBo getVehicleByStatusAndLicensePlate(VehicleCreateCmd vehicleCreateCmd);


     VehicleBo findByLicensePlate(String licensePlate);


     VehicleBo findByLicensePlateAndBrandModelAndYearAndStatus(String licensePlate, String brandModel, String year, String statusStr);


}
