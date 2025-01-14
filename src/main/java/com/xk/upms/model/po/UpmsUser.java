package com.xk.upms.model.po;

import java.io.Serializable;
import java.util.Date;

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
 * 用戶實體類，代表UPMS系統中的用戶。 提供用戶的基本信息，包括用戶名、郵箱、電話號碼、密碼以及帳戶狀態等。
 *
 * @author yuan Created on 2022/06/10.
 * @author yuan Updated on 2024/10/25 with code optimization based on GPT
 *         recommendations.
 */
@Entity
@Getter
@Setter
@Table(name = "upms_user")
public class UpmsUser extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@NotNull(groups = Update.class)
	@Comment("流水號")
	private Long id;

	@NotBlank(message = "用戶名稱不能為空")
	@Size(max = 50, message = "用戶名稱不能超過50個字符")
	@Column(unique = true, nullable = false)
	@Comment("用戶名稱")
	private String name;

	@NotBlank(message = "郵箱不能為空")
	@Size(max = 100, message = "郵箱不能超過100個字符")
	@Pattern(regexp = ".+@.+\\..+", message = "請輸入有效的郵箱地址")
	@Column(unique = true, nullable = false)
	@Comment("郵箱")
	private String email;
	
	
	@Comment("電話")
	@Pattern(regexp = "^[0-9]{10,15}$", message = "請輸入有效的電話號碼")
	private String cellPhone;
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("最後登入時間")
    private Date lastLogin;
	
	@NotBlank(message = "密碼不能為空")
	@Comment("密碼MD5(密碼+鹽)")
	private String password;
	
	@Comment("92_狀態(0:正常,1:锁定)")
	@Column(columnDefinition = "boolean default false")
	private Boolean locked;
	
	@Comment("創立者")
	private String creater;
	
	
	@Comment("創立時間")
	private Date createdTime;
	
	@Comment("修改者")
	private String updater;
	
	
	@Comment("創立時間")
	private Date updatedTime;

}