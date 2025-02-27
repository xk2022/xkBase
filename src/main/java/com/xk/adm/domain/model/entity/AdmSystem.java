package com.xk.adm.domain.model.entity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.xk.adm.domain.model.po.AdmSystemPO;
import com.xk.common.util.XkBeanUtils;

import lombok.Data;

@Data
public class AdmSystem {

	private UUID uuid;
	private String code;
	private String name;
	private String description;
	private boolean enabled;
	private boolean deleted;
	private ZonedDateTime deletedTime;

	public void disable() {
		this.enabled = false;
	}

	public void initialize() {
		this.enabled = true;
	}

	// 📌 軟刪除邏輯
	public void markAsDeleted() {
		this.deleted = true;
		this.deletedTime = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));
	}

	// 📌 轉換為持久化物件（PO）
	public AdmSystemPO toPO() {
		return XkBeanUtils.copyProperties(this, AdmSystemPO::new);
	}

}
