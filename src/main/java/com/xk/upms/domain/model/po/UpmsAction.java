package com.xk.upms.domain.model.po;

import com.xk.common.base.BaseEntity;
import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by Hank on 2025/02/21
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "upms_action")
@SQLDelete(sql = "UPDATE upms_action  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class UpmsAction extends SoftDeletableEntity implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "uuid", length = 36, nullable = false, updatable = false, unique = true)
	private UUID uuid;

	@Column(name = "name", length = 100)
	@Comment("01_動作名稱")
	private String name;

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

	@Column(name="active" , columnDefinition = "TINYINT(1) DEFAULT 1")
	@Comment("是否啟用")
	private Boolean active;


	/** 📌 刪除的使用者 */
	@Size(max = 50, message = "用戶名稱不能超過50個字符")
	@Column(name = "deleted_user", unique = true)
	@Comment("04_刪除的使用者名稱")
	private String deleteUser;


}
