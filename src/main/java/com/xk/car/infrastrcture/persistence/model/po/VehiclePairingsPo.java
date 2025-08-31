package com.xk.car.infrastrcture.persistence.model.po;


import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 車頭與板車管理
 * hank create 8/31
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_pairings")
@Entity
@SQLDelete(sql = "UPDATE vehicle_pairings  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class VehiclePairingsPo extends SoftDeletableEntity implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid" ,nullable = false , updatable = false ,unique = true , length = 36)
    private UUID uuid;

    @Column(name = "head_id")
    @Comment("車頭 vehicle_id")
    private String headId;

    @Column(name = "trailer_id")
    @Comment("板車 vehicle_id")
    private String trailerId;

    @Column(name="bind_time")
    @Comment("綁定時間")
    private ZonedDateTime bindTime;


    @Column(name="unbind_time")
    @Comment("解除時間（仍綁定中則為 NULL）")
    private ZonedDateTime unbindTime;


    @Column(name ="bind_by")
    @Comment("綁定操作人 ID")
    private String bindBy;

    @Column(name ="unbind_by")
    @Comment("解除操作人 ID（可為 NULL）")
    private String unbindBy;


    @Column(name ="note")
    @Comment("備註")
    private String note;
}
