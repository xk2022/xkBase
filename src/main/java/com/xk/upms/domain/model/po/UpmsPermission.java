package com.xk.upms.domain.model.po;

import com.xk.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.ZonedDateTime;

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
     * 名稱
     */
    @Comment("父權限id")
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
     * 排序
     */
    @Comment("排序")
    @Column(name = "orders")
    private Long orders;

    /** 📌 刪除狀態（0:刪除, 1:未刪除） */
    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    @ColumnDefault("1")
    @Comment("93_鎖定狀態（0:刪除, 1:未刪除）")
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