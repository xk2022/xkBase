package com.xk.car.application.usecase.impl;

import com.xk.car.application.converter.VehicleTripLogsConverter;
import com.xk.car.application.model.VehicleTripLogsRequest;
import com.xk.car.application.model.VehicleTripLogsResponse;
import com.xk.car.application.usecase.VehicleTripLogsCreateUseCase;
import com.xk.car.domain.model.bo.VehicleBo;
import com.xk.car.domain.model.bo.VehicleTripLogsBo;
import com.xk.car.domain.service.VehicleService;
import com.xk.car.domain.service.VehicleTripLogsService;
import com.xk.common.util.DateCoverUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * 📌 `VehicleTripLogsCreateUseCaseImpl` - 负责里程紀錄的创建逻辑
 *
 * - 处理 `VehicleTripLogsRequest` 并转换为 `VehicleTripLogs`
 * - 通过 `VehicleTripLogsService` 进行业务验证和存储
 * - 返回 `VehicleTripLogsResponse`
 *
 * @author hank  Created on 2025/08/15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleTripLogsCreateUseCaseImpl implements VehicleTripLogsCreateUseCase {
    private final VehicleTripLogsConverter converter;
    private final VehicleTripLogsService service;
    private final DateCoverUtils dateCoverUtils;
    private final VehicleService vehicleService;


    @Override
    public VehicleTripLogsResponse create(VehicleTripLogsRequest request) throws ParseException {
        //查詢車輛資訊
        VehicleBo vehicleBo = vehicleService.findByLicensePlate(request.getLicensePlate());
        log.info("[UseCase] {}里程紀錄 ,request={}" ,request.getUuid()==null?"建立":"更新" ,request);


        BigDecimal startMileage = new BigDecimal(request.getStartMileage());
        BigDecimal endMileage = new BigDecimal(request.getEndMileage());

        Date date = dateCoverUtils.StringCoverToDate(request.getDate());

        var cmd = converter.toCreateCmd(request);
        cmd.setStartMileage(startMileage);
        cmd.setEndMileage(endMileage);
        cmd.setDistance(endMileage.divide(startMileage));
        cmd.setDate(date);
        cmd.setVehicleType(vehicleBo.getVehicleType());
        cmd.setCarId(String.valueOf(vehicleBo.getUuid()));


        VehicleTripLogsBo result =  (request.getUuid() ==null)
                ?service.create(cmd)
                :service.update(vehicleBo.getUuid(),cmd);

        VehicleTripLogsResponse response = converter.toResponse(result);
        response.setStartMileage(String.valueOf(startMileage));
        response.setEndMileage(String.valueOf(endMileage));
        response.setDistance(String.valueOf(endMileage.subtract(startMileage)));
        response.setVehicleType(String.valueOf(result.getVehicleType()));
        response.setDate(String.valueOf(date));


        return response;
    }
}
