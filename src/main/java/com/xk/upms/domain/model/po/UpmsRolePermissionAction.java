package com.xk.upms.domain.model.po;

import com.xk.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "upms_role_permission_action")
public class UpmsRolePermissionAction extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_action_id", updatable = false, nullable = false)
    @Comment("00_流水號")
    private Long id;

    @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", length = 36, nullable = false)
    private UUID uuid;

    @Comment("角色uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "role_uuid", length = 36, nullable = false)
    private UUID roleUuid;

    @Comment("權限uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "permission_uuid", length = 36, nullable = false)
    private UUID permissionUuid;

    @Comment("動作uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "action_uuid", length = 36, nullable = false)
    private UUID actionUuid;

    @Column(name = "active")
    private Boolean active;

    /** 📌 刪除狀態（0:刪除, 1:未刪除） */
    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    @ColumnDefault("1")
    @Comment("是否刪除狀態（0:刪除, 1:未刪除）")
    private Boolean isDeleted = false;

    /** 📌 刪除的使用者 */
    @Size(max = 50, message = "用戶名稱不能超過50個字符")
    @Column(name = "deleted_user", unique = true)
    @Comment("04_刪除的使用者名稱")
    private String deleteUser;

    /** 📌 記錄用戶被刪除的時間（記錄登入歷史） */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_time")
    @Comment("05_用戶被刪除的時間")
    private ZonedDateTime deleteTime;

}
