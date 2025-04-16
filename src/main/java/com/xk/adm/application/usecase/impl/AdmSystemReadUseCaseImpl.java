package com.xk.adm.application.usecase.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xk.adm.application.dto.AdmSystemRequest;
import com.xk.adm.application.dto.AdmSystemResponse;
import com.xk.adm.application.usecase.AdmSystemReadUseCase;
import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.bo.AdmSystemSearchBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.handler.BusinessException;
import com.xk.common.util.XkBeanUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemReadUseCaseImpl`
 * 
 * - `AdmSystemReadUseCase` 具體業務邏輯的實作類  
 * - 依賴 `AdmSystemService` 處理業務邏輯  
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemReadUseCaseImpl implements AdmSystemReadUseCase {

	private final AdmSystemService admSystemService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdmSystemResponse> getList() {
		log.info(" [UseCase] 查詢所有系統...");
		
		List<AdmSystemBO> systems = admSystemService.findAll();
		log.info("✅ 查詢完成，共 {} 筆", systems.size());
		return XkBeanUtils.copyListProperties(systems, AdmSystemResponse::new);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdmSystemResponse> searchSystems(AdmSystemRequest request) {
	    log.info(" [UseCase] 依條件查詢系統: {}", request);

	    // 轉換 DTO -> BO，確保 Service 層不接觸 DTO
	    AdmSystemSearchBO systemBO = XkBeanUtils.copyProperties(request, AdmSystemSearchBO::new);
	    // 呼叫 Service 層
	    List<AdmSystemBO> filteredSystems = admSystemService.search(systemBO);
	    log.info("✅ 查詢完成，共 {} 筆", filteredSystems.size());
	    // 轉換 BO -> DTO 返回結果
	    return XkBeanUtils.copyListProperties(filteredSystems, AdmSystemResponse::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdmSystemResponse getSystemByUuid(UUID uuid) {
	    log.info(" [UseCase] 依 UUID 查詢系統: {}", uuid);

		AdmSystemBO systemBO = admSystemService.findByUuid(uuid)
	            .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "❌ 系統不存在: " + uuid));

		return XkBeanUtils.copyProperties(systemBO, AdmSystemResponse::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdmSystemResponse getSystemByCode(String code) {
		log.info(" [UseCase] 依 CODE 查詢系統: {}", code);

		AdmSystemBO systemBO = admSystemService.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException("❌ 系統不存在: " + code));
		log.info("✅ 查詢成功: {}", systemBO.getName());

		return XkBeanUtils.copyProperties(systemBO, AdmSystemResponse::new);
	}

}
