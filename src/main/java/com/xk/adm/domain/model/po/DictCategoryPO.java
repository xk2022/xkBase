package com.xk.adm.domain.model.po;

import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

/**
 *  選單類別
 *   * - 提供 **軟刪除（Soft Delete）**
 *  * - **Hibernate 6.3 以上** 使用 `@Filter` 取代 `@Where`
 *  * - 自動處理 **刪除標記** 與 **刪除時間**
 * @author hank.lin create 2025/09/02
 */
@Getter
@Setter
@Entity
@Table(name = "dict_category ")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dict_category  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class DictCategoryPO extends SoftDeletableEntity implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Column(name="code" , nullable = false ,unique = true)
    @Comment("穩定代碼，供API與程式使用")
    private String code;

    @Column(name="name" , nullable = false )
    @Comment("顯示名稱（後台用）")
    private String name;

    @Column(name= "description" )
    @Comment("說明描述")
    private String description;

}
