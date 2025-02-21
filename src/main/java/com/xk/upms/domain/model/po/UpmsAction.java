package com.xk.upms.domain.model.po;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import com.xk.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Hank on 2025/02/21
 */
@Entity
@Getter
@Setter
@Table(name = "upms_action")
public class UpmsAction extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "action_id", updatable = false, nullable = false)
	@Comment("00_流水號") // 描述
	private Long id;

	@Column(name = "actionName", length = 100)
	@Comment("01_動作名稱") // create,update,delete,read,write
	private String actionName;

	@Column(name = "permissionId", length = 100)
	@Comment("02_permissionId")
	private Long permissionId;

	@Column(name = "url", length = 100)
	@Comment("03_路徑")
	private String url;

	@Column(name ="orders" ,nullable = false, columnDefinition = "BIGINT DEFAULT 0")
	@ColumnDefault("0")
	@Comment("04_資料排序")
	private Long orders = 0L;
	
	@Column(name = "method", length = 100)
	@Comment("03_RestfulMethod")
	private String method;
	
	
	@Column(name="active",nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	@ColumnDefault("0")
	@Comment("93_鎖定狀態（0:關閉, 1:開啟）")
	private Boolean active = false;
	
	/** 📌 刪除狀態（0:刪除, 1:未刪除） */
	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	@ColumnDefault("1")
	@Comment("93_鎖定狀態（0:刪除, 1:未刪除）")
	private Boolean isDeleted = false;

	/** 📌 刪除的使用者 */
	@Size(max = 50, message = "用戶名稱不能超過50個字符") //
	@Comment("04_刪除的使用者名稱")
	private String deleteUser;

	/** 📌 記錄用戶被刪除的時間（記錄登入歷史） */
	@Temporal(TemporalType.TIMESTAMP)
	@Comment("05_用戶被刪除的時間")
	private ZonedDateTime deleteTime;

}
