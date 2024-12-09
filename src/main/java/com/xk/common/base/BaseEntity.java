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
 * 通用字段基类，为实体类提供常见的审计字段和分组校验功能。
 * <p>
 * 可根据需要扩展字段，如状态(status)、排序(orders)、是否锁定(locked)等。
 * </p>
 *
 * @author Zheng Jie
 * @date 2019年10月24日
 * @version 优化版 yuan 2024
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

//    @Column(name = "orders")
//    @Comment("90_資料排序")
//    private Long orders;

//    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
//    @ColumnDefault("true")
//    @Comment("91_狀態（啟用/禁用）")
//    private Boolean status = true;

//    @Column(name = "locked", columnDefinition = "boolean default false")
//    @Comment("92_狀態(0:正常,1:锁定)")
//    private Boolean locked;

	@ApiModelProperty(value = "創建者", required = true, example = "admin")
	@CreatedBy
	@Column(name = "create_by", updatable = false)
	@Comment("96_創建者")
	private String createBy;

	@ApiModelProperty(value = "創建時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
	@CreationTimestamp
	@Column(name = "create_time", updatable = false)
//	@Temporal(TemporalType.TIMESTAMP)
	@Comment("97_創建時間")
	private ZonedDateTime createTime;

	@ApiModelProperty(value = "編輯者", required = true, example = "admin")
	@LastModifiedBy
	@Column(name = "update_by")
	@Comment("98_編輯者")
	private String updateBy;

	@ApiModelProperty(value = "編輯時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
	@UpdateTimestamp
	@Column(name = "update_time", nullable = true) // Make the column nullable
	@Temporal(TemporalType.TIMESTAMP)
	@Comment("99_編輯時間")
	private ZonedDateTime updateTime;

	/* 分组校验 */
	public @interface Create {
	}

	/* 分组校验 */
	public @interface Update {
	}

}
