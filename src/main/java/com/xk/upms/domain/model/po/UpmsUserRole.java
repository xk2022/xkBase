package com.xk.upms.domain.model.po;

import com.xk.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by Hank on 2025/01/13
 */
@Entity
@Getter
@Setter
@Table(name = "upms_user_role")
public class UpmsUserRole extends BaseEntity implements Serializable {

    /**
     * 流水號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "faceset_generator", strategy = "guid")
    @Column(name = "user_role_id")
    @NotNull(groups = Update.class)
    private Long id;

    @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", length = 36, nullable = false)
    private UUID uuid;

    @Comment("使用者uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name="user_uuid", length = 36, nullable = false)
    private UUID userUuid;

    @Comment("角色uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "role_uuid", length = 36, nullable = false)
    private UUID roleUuid;

    /** 📌 刪除狀態（0:刪除, 1:未刪除） */
    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    @ColumnDefault("1")
    @Comment("93_鎖定狀態（0:刪除, 1:未刪除）")
    private Boolean isDeleted = false;

    /** 📌 刪除的使用者 */
    @Size(max = 50, message = "用戶名稱不能超過50個字符") //
    @Column(name = "deleted_user", unique = true)
    @Comment("04_刪除的使用者名稱")
    private String deleteUser;

    /** 📌 記錄用戶被刪除的時間（記錄登入歷史） */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_time")
    @Comment("05_用戶被刪除的時間")
    private ZonedDateTime deleteTime;

}