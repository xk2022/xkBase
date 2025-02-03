package com.xk.upms.domain.model.po;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import com.xk.common.base.BaseEntity;
import com.xk.upms.domain.model.enums.PermissionAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
    @Column(nullable = false)
    private Long roleId;
    /**
     * 权限编号
     */
    @Column(nullable = false)
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

}