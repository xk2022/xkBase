package com.xk.adm.domain.model.po;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.xk.common.base.BaseEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@Table(name = "adm_system")
@SQLDelete(sql = "UPDATE adm_system SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted") // ✅ 替代 @Where
public class AdmSystem extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, length = 36)
	@Comment("00_流水號") /** 系統唯一識別碼 (UUID) */
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

	/** 軟刪除處理 */
	@PreRemove
	public void markAsDeleted() {
		this.deleted = true;
		this.deletedTime = ZonedDateTime.now();
	}

}