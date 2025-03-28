package com.xk.upms.model.po;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import com.xk.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;




@SuppressWarnings("deprecation")
@Entity
@Getter
@Setter
@Table(name = "upms_permission")
public class UpmsPermission extends BaseEntity implements Serializable  {
	
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
    private Long systemId;
    /**
     * 所屬上層
     */
    private Long pid;
    /**
     * 名稱
     */
    private String name;
    /**
     * 類型(1:目錄,2:菜單,3:按鈕)
     */
    private Byte type;
    /**
     * 權限值
     */
    private String permissionValue;
    /**
     * 路徑
     */
    private String uri;
    /**
     * 圖標
     */
//    private String icon;
    /**
     * 狀態(0:禁止,1:正常)
     */
    private Boolean status;
    /**
     * 排序
     */
    private Long orders;


}
