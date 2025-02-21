package com.xk.upms.domain.model.po;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.xk.common.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Hank on 2025/01/13
 */
@Entity
@Getter
@Setter
@Table(name = "upms_permission")
public class UpmsPermission extends BaseEntity implements Serializable {
    /**
     * 流水號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "faceset_generator", strategy = "guid")
    @Column(name = "permission_id")
    @NotNull(groups = Update.class)
    private Long id;
    /**
     * 所屬系統
     */
    @Column(name = "system_id")
    @Comment("所屬系統")
    private Long systemId;
    /**
     * 所屬上層
     */
    @Comment("所屬上層")
    @Column(name = "pid")
    private Long pid;
    /**
     * 名稱
     */
    @Comment("權限名稱")
    @Column(name = "name")
    private String name;
    /**
     * 路徑
     */
    @Column(name = "uri")
    @Comment("路徑")
    private String uri;
    /**
     * 圖標
     */
//    private String icon;
    /**
     * 狀態(0:禁止,1:正常)
     */
    @Comment("狀態(0:禁止,1:正常)")
    @Column(name = "status")
    private Boolean status;
    /**
     * 排序
     */
    @Comment("排序")
    @Column(name = "orders")
    private Long orders;
    
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
	
	/**
     * 子權限列表
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UpmsPermission> children = new ArrayList<>();
    
    /**
     * 父權限
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    private UpmsPermission parent;

}