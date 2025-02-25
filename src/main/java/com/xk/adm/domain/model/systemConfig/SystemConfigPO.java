package com.xk.adm.domain.model.systemConfig;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.Comment;

import com.xk.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 📌 `SystemConfigPO` - JPA 持久層物件 (Persistence Object)
 * 
 * @author yuan Created on 2025/02/24.
 */
@Entity
@Table(name = "system_config")
@Getter
@Setter
public class SystemConfigPO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // ✅ `UUID` 使用 AUTO
	@Column(name = "system_config_id", updatable = false, nullable = false)
	@Comment("00_流水號")
	private UUID id;

	@NotBlank(message = "系統名稱不能為空")
	@Column(nullable = false)
	@Comment("01_系統名稱")
	private String systemName;

	@NotBlank(message = "應用時區不能為空")
	@Column(nullable = false)
	@Comment("02_應用時區")
	private String timeZone;

	@NotBlank(message = "預設語言不能為空")
	@Column(nullable = false)
	@Comment("03_預設語言")
	private String language;

	@NotBlank(message = "預設幣別不能為空")
	@Column(nullable = false)
	@Comment("04_預設幣別")
	private String currency;

}