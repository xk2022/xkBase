package com.xk.upms.domain.model.po;

import com.xk.common.base.BaseEntity;
import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "upms_role_system")
@SQLDelete(sql = "UPDATE upms_role_system  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class UpmsRoleSystem extends SoftDeletableEntity implements Serializable  {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
    private UUID uuid;

    @Comment("角色uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "role_uuid", length = 36, nullable = false)
    private UUID roleUuid;

    @Comment("系統uuid")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "system_uuid", length = 36, nullable = false)
    private UUID systemUuid;

    /** 📌 刪除的使用者 */
    @Size(max = 50, message = "用戶名稱不能超過50個字符")
    @Column(name = "deleted_user", unique = true)
    @Comment("04_刪除的使用者名稱")
    private String deleteUser;



}
