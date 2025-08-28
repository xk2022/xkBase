package com.xk.car.infrastrcture.persistence.model.po;


import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE vehicle  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class VehiclePo extends SoftDeletableEntity implements Serializable {


    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @NotNull
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

    @NotNull
    @Column(name = "status" , nullable = false)
    @Comment("車輛狀態")
    private VehicleStatusEnum status;






}
