package com.xk.adm.application.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xk.adm.application.model.AdmSystemDTO;
import com.xk.adm.application.usecase.AdmSystemFindUseCase;
import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.util.XkBeanUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemUseCaseImpl`
 * 
 * - `AdmSystemUseCase` 具體業務邏輯的實作類
 * - 依賴 `AdmSystemService` 處理業務邏輯
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemFindUseCaseImpl implements AdmSystemFindUseCase {

    private final AdmSystemService admSystemService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdmSystemDTO> getList() {
        log.info("📌 查詢所有系統");

        List<AdmSystemBO> systems = admSystemService.getAllSystems();
        return XkBeanUtils.copyListProperties(systems, AdmSystemDTO::new);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdmSystemDTO> searchSystems(AdmSystemDTO request) {
		log.info("📌 查詢所有系統: {}", request);

		AdmSystemBO systemBO = XkBeanUtils.copyProperties(request, AdmSystemBO::new);
        List<AdmSystemBO> filteredSystems = admSystemService.searchSystems(systemBO);
		return XkBeanUtils.copyListProperties(filteredSystems, AdmSystemDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdmSystemDTO getSystemById(String uuid) {
        log.info("📌 依 UUID 查詢系統: {}", uuid);
		// 🔥 查詢
		AdmSystemBO systemBO = admSystemService.findById(uuid)
				.orElseThrow(() -> new EntityNotFoundException("系統不存在: " + uuid));
		return XkBeanUtils.copyProperties(systemBO, AdmSystemDTO::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdmSystemDTO getSystemByCode(String code) {
		log.info("📌 查詢系統 by CODE: {}", code);
		// 🔥 查詢
		AdmSystemBO systemBO = admSystemService.getSystemByCode(code)
				.orElseThrow(() -> new EntityNotFoundException("系統不存在: " + code));
		return XkBeanUtils.copyProperties(systemBO, AdmSystemDTO::new);
    }
    
}

