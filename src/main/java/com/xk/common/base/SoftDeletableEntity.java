package com.xk.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.ZonedDateTime;

/**
 * 📌 SoftDeletableEntity
 * - 提供軟刪除欄位 (deleted, deletedTime)
 * - 提供 Hibernate Filter 支援
 * <p>
 * ⚠️ 注意：FilterDef 名稱在全域必須唯一
 */
@Getter
@Setter
@MappedSuperclass
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public abstract class SoftDeletableEntity extends BaseEntity {

    /**
     * 軟刪除標記
     */
    @Column(name = "deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    @ColumnDefault("0")
    @Comment("93_刪除標記（0:正常, 1:已刪除）")
    private Boolean deleted = false;

    @Schema(description = "刪除時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @Column(name = "delete_time", nullable = true) // Make the column nullable
    @Comment("94_刪除時間(軟刪除)")
    private ZonedDateTime deletedTime;

}
