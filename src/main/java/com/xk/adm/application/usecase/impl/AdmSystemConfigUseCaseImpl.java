package com.xk.adm.application.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.adm.application.dto.SystemConfigDTO;
import com.xk.adm.application.usecase.AdmSystemConfigUseCase;
import com.xk.adm.domain.model.bo.SystemConfigBO;
import com.xk.adm.domain.service.AdmSystemConfigService;
import com.xk.common.handler.BusinessException;
import com.xk.common.util.XkBeanUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `SystemConfigUseCaseImpl` - `SystemConfigUseCase` 具體實作
 * 
 * - 獲取系統設定 - 更新系統設定 - 確保資料庫一致性（使用 `@Transactional`）
 * 
 * @author yuan Created on 2025/02/24.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemConfigUseCaseImpl implements AdmSystemConfigUseCase {

	private final AdmSystemConfigService admSystemConfigService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SystemConfigDTO getSystemSettings() {
		log.info("📌 開始獲取系統設定");

		SystemConfigBO configBO = admSystemConfigService.findLatest()
				.orElseThrow(() -> new EntityNotFoundException("未找到系統設定"));

		log.info("✅ 成功獲取系統設定: {}", configBO);
		return XkBeanUtils.copyProperties(configBO, SystemConfigDTO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public SystemConfigDTO updateSettings(Long uuid, SystemConfigDTO request) {
		log.info("📌 更新系統設定 ID: {}", uuid);

		validateConfig(request); // ✅ 檢查數據合法性

		SystemConfigBO systemConfig = admSystemConfigService.findLatest().orElse(new SystemConfigBO()); // 若找不到則創建新實體

		systemConfig.setSystemName(request.getSystemName());
		systemConfig.setTimeZone(request.getTimeZone());
		systemConfig.setLanguage(request.getLanguage());
		systemConfig.setCurrency(request.getCurrency());

		SystemConfigBO savedEntity = admSystemConfigService.save(systemConfig); // ✅ 儲存到 DB

		log.info("✅ 成功更新系統設定: {}", savedEntity);
		return XkBeanUtils.copyProperties(savedEntity, SystemConfigDTO::new);
	}

	/**
	 * 🔹 檢查設定值是否合法
	 * 
	 * @param config {@link SystemConfigDTO}
	 * @throws BusinessException 若設定不符合要求
	 */
	private void validateConfig(SystemConfigDTO config) {
		if (config.getSystemName() == null || config.getSystemName().isEmpty()) {
			throw new BusinessException("系統名稱不可為空");
		}
		if (config.getTimeZone() == null || config.getTimeZone().isEmpty()) {
			throw new BusinessException("時區不可為空");
		}
		if (config.getLanguage() == null || config.getLanguage().isEmpty()) {
			throw new BusinessException("語言設定不可為空");
		}
		if (config.getCurrency() == null || config.getCurrency().isEmpty()) {
			throw new BusinessException("貨幣設定不可為空");
		}
	}

	/**
	 * 📌 初始化系統設定
	 */
	@Override
	@Transactional
	public SystemConfigDTO create() {
		log.info("📌 初始化 SystemConfig 資料...");

		// 檢查是否已有系統設定，若無則初始化
		if (admSystemConfigService.findLatest().isPresent()) {
			log.info("✅ SystemConfig 已存在，跳過初始化");
			return null;
		}
		SystemConfigBO request = new SystemConfigBO();
		request.setId(UUID.randomUUID().toString()); // 使用 UUID
		request.setSystemName("XploreKaleidoscope 管理系統");
		request.setTimeZone("Asia/Taipei");
		request.setLanguage("zh-TW");
		request.setCurrency("TWD");
		admSystemConfigService.save(request);
		return XkBeanUtils.copyProperties(request, SystemConfigDTO::new);
	}

}
