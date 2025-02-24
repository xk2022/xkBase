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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 📌 用戶實體類（UPMS 系統）
 * 
 * - 代表系統中的用戶，包含基本信息，如用戶名、郵箱、電話號碼、密碼、登入狀態等。 - 可根據需求擴展，例如：角色、權限關聯、雙因素驗證等。
 * 
 * @author Hank Created on 2022/01/13.
 * @author yuan Updated on 2025/02/14 something note here.
 */
@Entity
@Getter
@Setter
@Table(name = "upms_user")
public class UpmsUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@NotNull(groups = Update.class)
	@Comment("00_流水號")
	private Long id;

	@NotBlank(message = "用戶名稱不能為空")
	@Size(max = 50, message = "用戶名稱不能超過50個字符") //
	@Column(nullable = false)
	@Comment("01_用戶名稱")
	private String username;

	@NotBlank(message = "郵箱不能為空")
	@Size(max = 100, message = "郵箱不能超過100個字符")
	@Pattern(regexp = ".+@.+\\..+", message = "請輸入有效的郵箱地址")
	@Column(nullable = false)
	@Comment("02_郵箱")
	private String email;

	@Comment("03_電話")
	@Pattern(regexp = "^[0-9]{10,15}$", message = "請輸入有效的電話號碼")
	private String cellPhone;

//  @Comment("04_鹽")
//  private String salt;

	@NotBlank(message = "密碼不能為空")
	@Comment("05_密碼MD5(密碼+鹽)")
	private String password;

	/** 📌 記錄用戶最後登入時間（記錄登入歷史） */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	@Comment("06_最後登入時間")
	private ZonedDateTime lastLogin;

	/** 📌 登入失敗次數（防止暴力破解） */
	@Column(name = "failed_attempts", nullable = false, columnDefinition = "INT DEFAULT 0")
	@ColumnDefault("0")
	@Comment("07_登入失敗次數")
	private Integer failedAttempts = 0;

	/** 📌 啟用狀態（0:未啟用, 1:啟用，用於帳號啟用控制） */
	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	@ColumnDefault("1")
	@Comment("91_啟用狀態（0:未啟用, 1:啟用）")
	private Boolean enabled = true;

	/** 📌 鎖定狀態（0:正常, 1:鎖定，用於安全鎖定） */
	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	@ColumnDefault("0")
	@Comment("92_鎖定狀態（0:正常, 1:鎖定）")
	private Boolean locked = false;

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