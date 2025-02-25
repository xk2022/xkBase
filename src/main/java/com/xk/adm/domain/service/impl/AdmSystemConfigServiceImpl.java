package com.xk.adm.domain.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.adm.domain.dao.AdmSystemConfigRepository;
import com.xk.adm.domain.model.systemConfig.SystemConfigBO;
import com.xk.adm.domain.model.systemConfig.SystemConfigPO;
import com.xk.adm.domain.service.AdmSystemConfigService;
import com.xk.common.util.XkBeanUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `SystemConfigServiceImpl` - `SystemConfigService` 的具體實作
 * 
 * - 獲取最新系統設定 - 根據 UUID 查找設定 - 儲存或更新系統設定 - 確保資料一致性 (`@Transactional`)
 * 
 * @author yuan Created on 2025/02/24.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemConfigServiceImpl implements AdmSystemConfigService {

	private final AdmSystemConfigRepository admSystemConfigRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<SystemConfigBO> findLatest() {
		log.info("📌 查詢最新的系統設定");
		return admSystemConfigRepository.findTopByOrderByIdDesc()
				.map(entity -> new SystemConfigBO(
						entity.getId().toString(), 
						entity.getSystemName(), 
						entity.getTimeZone(), 
						entity.getLanguage(), 
						entity.getCurrency()
				));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<SystemConfigBO> findById(UUID id) {
		log.info("📌 查找系統設定，UUID: {}", id);
		return admSystemConfigRepository.findById(id)
				.map(entity -> new SystemConfigBO(
						entity.getId().toString(), 
						entity.getSystemName(), 
						entity.getTimeZone(), 
						entity.getLanguage(), 
						entity.getCurrency()
				));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public SystemConfigBO save(SystemConfigBO systemConfigBO) {
		log.info("📌 儲存/更新系統設定: {}", systemConfigBO);
		SystemConfigBO reslutBo = new SystemConfigBO();

		// **將 BO 轉換為 PO**
		SystemConfigPO requestPO = XkBeanUtils.copyProperties(systemConfigBO, SystemConfigPO::new);
		// **存入 DB**
		SystemConfigPO savedPO = admSystemConfigRepository.save(requestPO);

		XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
		// **轉回 BO 回傳**
		return reslutBo;
	}

}