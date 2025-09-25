package com.xk.car.infrastrcture.persistence.model.po;


import com.xk.car.domain.model.enums.VehicleEnum;
import com.xk.car.domain.model.enums.VehicleStatusEnum;
import com.xk.common.base.SoftDeletableEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *車輛狀態管理
 * Hank create 8/11
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_status_logs")
@SQLDelete(sql = "UPDATE vehicle_status_logs  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class VehicleStatusLogsPo extends SoftDeletableEntity implements Serializable {



    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
    private UUID uuid;

    @Column(name = "car_id", nullable = false)
    @Comment("關聯車輛")
    private String carId;

    @NotNull
    @Column(name = "vehicle_type" ,nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("車頭/板車")
    private VehicleEnum vehicleType;

    @Column(name="driver_id")
    @Comment("司機ID")
    private String driverId;

    @Column(name = "status" , nullable = false)
    @Comment("車輛狀態")
    private VehicleStatusEnum status;

    @Column(name = "operator_id" , nullable = false)
    @Comment("操作者 ID")
    private Integer operatorId;

    @Column(name = "status_note")
    @Comment("狀態說明")
    private String statusNote;

}
