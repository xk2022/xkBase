package com.xk.upms.domain.model.po;

import com.xk.common.base.BaseEntity;
import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by Hank on 2025/01/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "upms_permission")
@SQLDelete(sql = "UPDATE upms_permission  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class UpmsPermission extends SoftDeletableEntity implements Serializable  {



    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", length = 36, nullable = false, updatable = false, unique = true)
    private UUID uuid;

    /**
     * 父權限id
     */
    @Comment("父權限uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "p_uuid", length = 36)
    private UUID pUuid;

    /**
     * 名稱
     */
    @Comment("權限名稱")
    @Column(name = "name")
    private String name;

    /**
     * 路徑
     */
    @Column(name = "uri")
    @Comment("路徑")
    private String uri;

    /**
     * "狀態(0:禁止,1:正常)
     */
    @Column(name = "status")
    @Comment("狀態(0:禁止,1:正常)")
    private Boolean status;

    /**
     * 排序
     */
    @Comment("排序")
    @Column(name = "orders")
    private Long orders;


    /** 📌 刪除的使用者 */
    @Size(max = 50, message = "用戶名稱不能超過50個字符") //
    @Column(name = "deleted_user", unique = true)
    @Comment("04_刪除的使用者名稱")
    private String deleteUser;



}