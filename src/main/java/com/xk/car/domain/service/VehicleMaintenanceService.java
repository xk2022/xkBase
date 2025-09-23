package com.xk.car.domain.service;

import com.xk.car.application.model.VehicleMaintenanceCreateCmd;
import com.xk.car.domain.model.bo.VehicleMaintenanceBo;

import java.util.List;
import java.util.UUID;

public interface VehicleMaintenanceService {

    /**
     *
     * @param cmd
     * @return
     */
    VehicleMaintenanceBo create(VehicleMaintenanceCreateCmd cmd);

    /**
     *
     * @param uuid
     * @param cmd
     * @return
     */
    VehicleMaintenanceBo update(UUID uuid, VehicleMaintenanceCreateCmd cmd);

    /**
     * 刪除車輛維修提醒資訊
     * @param uuid
     */
    void delete(UUID uuid);


    /**
     * 查詢維修提醒紀錄
     * @param uuid
     * @return
     */
    List<VehicleMaintenanceBo> getMaintenanceByCarId(UUID uuid);
}
