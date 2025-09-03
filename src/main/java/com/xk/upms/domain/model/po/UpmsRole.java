package com.xk.upms.domain.model.po;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import com.xk.common.base.BaseEntity;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.SqlTypes;

/**
 * UpmsRole 實體類 - 角色管理
 *
 * @author Hank Created on 2025/01/13.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "upms_role")
@SQLDelete(sql = "UPDATE upms_role  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class UpmsRole extends SoftDeletableEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
	private UUID uuid;

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


	/** 📌 刪除的使用者 */
	@Size(max = 50, message = "用戶名稱不能超過50個字符") //
	@Column(name = "deleted_user", unique = true)
	@Comment("04_刪除的使用者名稱")
	private String deleteUser;


}
