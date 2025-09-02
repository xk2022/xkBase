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
 *  選單項目
 * @author hank.lin create 2025/09/02
 */
@Getter
@Setter
@Entity
@Table(name = "dict_item ")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dict_item  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class DictItemPO extends SoftDeletableEntity implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    private UUID uuid;


    @Column(name="category_code" ,nullable = false)
    @Comment("選單類別代碼")
    private String catrgoryCode;

    @Column(name="item_code" ,nullable = false)
    @Comment("選單項目代碼")
    private String itemCode;

    @Column(name="item_name" ,nullable = false)
    @Comment("選單項目名稱")
    private String itemName;

    @Column(name="sort_order" ,nullable = false)
    private Integer sortOrder;


}
