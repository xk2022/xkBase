package com.xk.car.domain.model.entity;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 *
 * 車輛基本資料登記
 *  Hank create 8/11
 *
 */
@Data
public class VehicleEntity {

    private Long vehicleId;
    private UUID uuid;
    private Boolean deleted = false;
    private VehicleEnum vehicleType;
    private String licensePlate;
    private String brandModel;
    private String year;
    private BigDecimal mileage;
    private VehicleStatusEnum status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private LocalDateTime deletedTime;


    /**
     * 初始化車輛資訊
     */
    public void initialize() {
        this.status = VehicleStatusEnum.IDLE;
        this.createdAt = ZonedDateTime.now();
    }


    public void updateStatus(String status){
        this.status = VehicleStatusEnum.fromString(status);
        this.updatedAt = ZonedDateTime.now();
    }

    public void delete(){
        if(this.status!= VehicleStatusEnum.IDLE){
            throw new IllegalStateException("只有 空閒狀態的車輛可以刪除");
        }

        this.deleted = true;
        this.deletedTime = LocalDateTime.now();

    }

    public void restore(){
        if(!Boolean.TRUE.equals(this.deleted)){
            throw new IllegalStateException("車輛不是刪除狀態 ,無法恢復");
        }
        this.deleted = false;
        this.deletedTime = null;
        this.status = VehicleStatusEnum.IDLE;
    }

}
