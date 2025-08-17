package com.xk.car.domain.model.entity;

import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 *
 * 車輛基本資料登記
 *  Hank create 8/11
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Vehicle")
@Entity
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", updatable = false, nullable = false)
    @Comment("流水號")
    private UUID uuid;

    @NotBlank
    @Column(name = "vehicle_type" ,nullable = false)
    @Comment("車頭/板車")
    private VehicleEnum vehicleType;

    @NotBlank
    @Column(name = "license_plate" ,nullable = false)
    @Comment("車牌號碼")
    private String licensePlate;

    @Column(name = "brand_model")
    @Comment("品牌與型號")
    private String brandModel;

    @Column(name="year")
    @Comment("出廠年份")
    private String year;

    @Column(name = "mileage")
    @Comment("初始里程數")
    private BigDecimal mileage;

    @NotBlank
    @Column(name = "status" , nullable = false)
    @Comment("車輛狀態")
    private VehicleStatusEnum status;

    @NotBlank
    @Schema(description = "創建時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false , nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("創建時間")
    private ZonedDateTime createdAt;

    @NotBlank
    @Schema(description = "編輯時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false) // Make the column nullable
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("編輯時間")
    private ZonedDateTime updatedAt;

}
