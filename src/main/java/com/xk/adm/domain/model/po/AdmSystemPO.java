package com.xk.adm.domain.model.po;

import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.entity.AdmSystem;
import com.xk.common.base.BaseEntity;
import com.xk.common.base.SoftDeletableEntity;
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
public class AdmSystemPO extends SoftDeletableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 數字主鍵（PK，內部用來關聯與索引）
	 * 使用 AUTO_INCREMENT，效能最佳
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_id", updatable = false, nullable = false)
	@Comment("00_流水號")
	private Long systemId;

	/**
	 * UUID 唯一識別碼（對外展示用，不可修改）
	 * 適合用於 API / 業務代碼
	 */
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
	@Comment("UUID_唯一識別碼")
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

	public AdmSystemBO toBO() {
	    AdmSystemBO bo = XkBeanUtils.copyProperties(this, AdmSystemBO::new);
//	    bo.setDeleted(this.deleted); // 确保业务对象能感知删除状态
	    return bo;
	}

	public AdmSystem toDomain() {
	    AdmSystem domain = XkBeanUtils.copyProperties(this, AdmSystem::new);
	    return domain;
	}

}