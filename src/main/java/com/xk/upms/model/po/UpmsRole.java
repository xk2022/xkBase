package com.xk.upms.model.po;

import java.io.Serializable;

import com.xk.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@Setter
@Table(name = "upms_user")
public class UpmsRole extends BaseEntity implements Serializable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false, nullable = false)
    @Comment("00_流水號") // 描述
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 100)
    @Comment("01_角色名稱")
    private String code;

    @Column(name = "title", nullable = false, length = 100)
    @Comment("02_角色標題")
    private String title;

    @Column(name = "description", length = 500)
    @Comment("03_角色描述")
    private String description;

    @Column(name = "orders", nullable = false)
    @Comment("04_排序")
    private Long orders = 0L; // 預設值

}
