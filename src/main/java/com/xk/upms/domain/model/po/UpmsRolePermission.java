package com.xk.upms.domain.model.po;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import com.xk.common.base.BaseEntity;
import com.xk.upms.domain.model.enums.PermissionAction;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Hank on 2025/01/13
 *
 * 联合唯一约束: @UniqueConstraint(columnNames = {"roleId", "permissionId"}) 确保同一个角色对同一个权限不会有重复的记录。
 */
@Entity
@Getter
@Setter
@Table(name = "upms_role_permission",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"roleId", "permissionId", "action"})}) // 设置唯一约束
public class UpmsRolePermission extends BaseEntity implements Serializable {
    /**
     * 流水號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "faceset_generator", strategy = "guid")
    @Column(name = "role_permission_id")
    @NotNull(groups = Update.class)
    private Long id;
    /**
     * 角色编号
     */
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    /**
     * 权限编号
     */
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;
    /**
     * 權限種類
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PermissionAction action;
    /**
     * 权限開關
     */
    @Column(nullable = false)
    private Boolean active;

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