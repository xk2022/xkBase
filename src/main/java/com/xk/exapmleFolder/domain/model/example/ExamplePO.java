package com.xk.exapmleFolder.domain.model.example;

import java.io.Serializable;

import org.hibernate.annotations.Comment;

import com.xk.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 📌 UserEntity
 * 
 * - `username`：使用者名稱（唯一）
 * - `email`：電子郵件（唯一）
 * - `password`：密碼（加密儲存）
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Entity
@Getter
@Setter
@Table(name = "example_user")
public class ExamplePO extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "example_user_id")
	@NotNull(groups = Update.class)
	@Comment("00_流水號")
    private Long id; // **使用者唯一 ID（自增主鍵）**

	@NotBlank(message = "用戶名稱不能為空")
	@Size(max = 50, message = "用戶名稱不能超過50個字符")
    @Column(nullable = false, unique = true, length = 50)
	@Comment("01_用戶名稱")
    private String username; // **使用者名稱**

	@NotBlank(message = "郵箱不能為空")
	@Size(max = 100, message = "郵箱不能超過100個字符")
	@Pattern(regexp = ".+@.+\\..+", message = "請輸入有效的郵箱地址")
    @Column(nullable = false, unique = true, length = 100)
	@Comment("02_郵箱")
    private String email; // **電子郵件**

	@NotBlank(message = "密碼不能為空")
    @Column(nullable = false)
	@Comment("05_密碼MD5(密碼+鹽)")
    private String password; // **加密密碼**

}
