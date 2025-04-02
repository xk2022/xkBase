package com.xk.upms.domain.model.po;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import com.xk.common.base.BaseEntity;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * UpmsRole 實體類 - 角色管理
 *
 * @author Hank Created on 2025/01/13.
 */
@Entity
@Getter
@Setter
@Table(name = "upms_role")
public class UpmsRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", updatable = false, nullable = false)
	@Comment("00_流水號") // 描述
	private Long id;

	@Column(name = "code", nullable = false, length = 100)
	@Comment("01_角色名稱")
	private String code;

	@Column(name = "title", nullable = false, length = 100)
	@Comment("02_角色標題")
	private String title;

	@Column(name = "description", length = 500)
	@Comment("03_角色描述")
	private String description;

	@Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
	@ColumnDefault("0")
	@Comment("89_資料排序")
	private Long orders = 0L;

	/** 📌 刪除狀態（0:刪除, 1:未刪除） */
	@Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	@ColumnDefault("1")
	@Comment("93_是否刪除狀態（0:刪除, 1:未刪除）")
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
