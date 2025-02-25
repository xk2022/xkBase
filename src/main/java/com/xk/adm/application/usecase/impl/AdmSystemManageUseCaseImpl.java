package com.xk.adm.application.usecase.impl;

import org.springframework.stereotype.Service;

import com.xk.adm.application.model.AdmSystemDTO;
import com.xk.adm.application.usecase.AdmSystemManageUseCase;
import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemManageUseCaseImpl`
 * 
 * - `AdmSystemManageUseCase` 具體業務邏輯的實作類
 * - 依賴 `AdmSystemService` 處理系統的創建、更新、刪除
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemManageUseCaseImpl implements AdmSystemManageUseCase {

    private final AdmSystemService admSystemService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AdmSystemDTO create(AdmSystemDTO request) {
		log.info("📌 開始創建新系統: {}", request.getName());
		// ✅ 轉換 DTO -> BO
		AdmSystemBO systemBO = XkBeanUtils.copyProperties(request, AdmSystemBO::new);
		// ✅ 儲存到 DB
		AdmSystemBO savedSystem = admSystemService.createSystem(systemBO);
		// ✅ 轉換 PO -> DTO 回傳
		return XkBeanUtils.copyProperties(savedSystem, AdmSystemDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdmSystemDTO update(AdmSystemDTO request) {
		log.info("📌 更新系統 UUID: {}", request.getUuid());
		// ✅ 檢查使用者是否存在
		AdmSystemBO existingSystemBO = admSystemService.findById(request.getUuid())
				.orElseThrow(() -> new EntityNotFoundException(String.format("系統 ID %d 不存在，更新失敗", request.getUuid())));
		// ✅ 更新必要欄位（但不影響 ID）
		GenericUpdateService<AdmSystemBO> updateSystemService = new GenericUpdateService<>();
		AdmSystemBO updatedSystemEntity = updateSystemService.updateEntity(existingSystemBO, request);
		// ✅ 儲存變更
		AdmSystemBO savedSystemEntity = admSystemService.update(updatedSystemEntity);
		// ✅ 回傳 DTO
		return XkBeanUtils.copyProperties(savedSystemEntity, AdmSystemDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean delete(String uuid) {
        log.info("📌 嘗試刪除系統 UUID: {}", uuid);
        
        boolean deleted = admSystemService.softDeleteSystem(uuid);
        
        if (deleted) {
            log.info("✅ 系統刪除成功，UUID: {}", uuid);
        } else {
            log.warn("⚠️ 系統 UUID: {} 不存在，刪除失敗", uuid);
        }
        return deleted;
    }
    
}

