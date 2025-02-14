package com.xk.common.base;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * 📌 通用基類 - 提供審計字段（創建者、編輯者、時間戳）與分組校驗
 * 
 * 📌 可根據需求擴展，例如：狀態(status)、排序(order)、是否鎖定(locked)。
 * 
 * @author Zheng Jie 2019年10月24日
 * @version 優化版 yuan 2024
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * TODO 以下列出常見的欄位，但並非每處都需要，故請複製到該Entity
	 */
//	@Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
//	@ColumnDefault("0")
//	@Comment("89_資料排序")
//	private Long orders = 0L;

//	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
//	@ColumnDefault("1")
//	@Comment("90_狀態（1:啟用, 0:禁用）")
//	private Boolean status = true;

//	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
//	@ColumnDefault("1")
//	@Comment("91_啟用狀態（0:未啟用, 1:啟用）")
//	private Boolean enabled = true;

//	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
//	@ColumnDefault("0")
//	@Comment("92_鎖定狀態（0:正常, 1:鎖定）")
//	private Boolean locked = false;

//	@Column(name = "deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
//	@ColumnDefault("0")
//	@Comment("93_刪除標記（0:正常, 1:已刪除）")
//	private Boolean deleted = false;

//	@Column(name = "type", nullable = true)
//	@Comment("94_類型（可根據具體需求定義）")
//	private String type;

//	@Column(name = "remark", length = 500)
//	@Comment("95_備註")
//	private String remark;

	@ApiModelProperty(value = "創建者", required = true, example = "admin")
	@CreatedBy
	@Column(name = "created_by", updatable = false, length = 50)
	@Comment("96_創建者")
	private String createdBy;

	@ApiModelProperty(value = "創建時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
	@CreationTimestamp
	@Column(name = "create_time", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Comment("97_創建時間")
	private ZonedDateTime createdTime;

	@ApiModelProperty(value = "編輯者", required = true, example = "admin")
	@LastModifiedBy
	@Column(name = "update_by")
	@Comment("98_編輯者")
	private String updatedBy;

	@ApiModelProperty(value = "編輯時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
	@UpdateTimestamp
	@Column(name = "update_time", nullable = true) // Make the column nullable
	@Temporal(TemporalType.TIMESTAMP)
	@Comment("99_編輯時間")
	private ZonedDateTime updatedTime;

	/* 分组校验 */
	public @interface Create {
	}

	/* 分组校验 */
	public @interface Update {
	}

}
