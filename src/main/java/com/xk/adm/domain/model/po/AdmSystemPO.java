package com.xk.adm.domain.model.po;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.entity.AdmSystem;
import com.xk.common.base.BaseEntity;
import com.xk.common.util.XkBeanUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 `AdmSystem`（管理系統）持久化對象（PO）
 * 
 * - 提供 **軟刪除（Soft Delete）**
 * - **Hibernate 6.3 以上** 使用 `@Filter` 取代 `@Where`
 * - 自動處理 **刪除標記** 與 **刪除時間**
 * 
 * @author yuan Created on 2025/02/25.
 */
@Entity
@Table(name = "adm_system", uniqueConstraints = {
    @UniqueConstraint(name = "uq_adm_system_code", columnNames = "code")
})
@Getter
@Setter
@SQLDelete(sql = "UPDATE adm_system SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted") // ✅ 替代 @Where
public class AdmSystemPO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_id", updatable = false, nullable = false)
	@Comment("00_流水號")
	private Long id;

	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, length = 36)
	@Comment("uuid") /** 系統唯一識別碼 (UUID) */
	private UUID uuid;

	@Column(nullable = false, unique = true)
	@Comment("01_系統代碼") /** 系統代碼 (唯一標識) */
	private String code;

	@Column(nullable = false)
	@Comment("02_系統名稱")
	private String name;

	@Comment("03_系統描述")
	private String description;

	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	@ColumnDefault("1")
	@Comment("91_啟用狀態（0:未啟用, 1:啟用）")
	private Boolean enabled = true;

	@Column(name = "deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	@ColumnDefault("0")
	@Comment("93_刪除標記（0:正常, 1:已刪除）") /** （軟刪除） */
	private Boolean deleted = false;

	@Schema(description = "刪除時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
	@Column(name = "delete_time", nullable = true) // Make the column nullable
	@Comment("94_刪除時間(軟刪除)")
	private ZonedDateTime deletedTime;

	public AdmSystemBO toBO() {
	    AdmSystemBO bo = XkBeanUtils.copyProperties(this, AdmSystemBO::new);
	    bo.setDeleted(this.deleted); // 确保业务对象能感知删除状态
	    return bo;
	}

	public AdmSystem toDomain() {
	    AdmSystem domain = XkBeanUtils.copyProperties(this, AdmSystem::new);
	    return domain;
	}

}