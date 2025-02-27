package com.xk.adm.application.usecase.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.adm.application.dto.AdmSystemCreateDTO;
import com.xk.adm.application.dto.AdmSystemResponse;
import com.xk.adm.application.usecase.AdmSystemCreateUseCase;
import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.bo.AdmSystemCreateBO;
import com.xk.adm.domain.model.bo.AdmSystemInitBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.util.XkBeanUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemCreateUseCaseImpl` - 负责系统管理的创建逻辑
 *
 * - 处理 `AdmSystemRequest` 并转换为 `AdmSystem`
 * - 通过 `AdmSystemService` 进行业务验证和存储
 * - 返回 `AdmSystemResponse`
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemCreateUseCaseImpl implements AdmSystemCreateUseCase {

    private final AdmSystemService admSystemService;
    
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public AdmSystemResponse create(AdmSystemCreateDTO createDTO) {
        log.info(" [UseCase] 開始創建系統 - Code: {}", createDTO.getCode());

        // undo by @Entity add 唯一索引提高效能
        // 確保代碼唯一（在 Service 層內部檢查）
//        if (admSystemService.existsByCode(createDTO.getCode())) {
//            log.warn("❌ [UseCase] 創建失敗，系統代碼已存在: {}", createDTO.getCode());
//            throw new BusinessException("系統代碼已存在: " + createDTO.getCode());
//        }
        // 轉換 DTO -> BO（商業物件）
        AdmSystemCreateBO createBO = XkBeanUtils.copyProperties(createDTO, AdmSystemCreateBO::new);
        // 調用 Domain Service 進行創建
        AdmSystemBO savedBO = admSystemService.create(createBO);
        log.info("✅ [UseCase] 系統創建成功 - ID: {}, Code: {}", savedBO.getUuid(), savedBO.getCode());
        // 轉換 BO -> DTO 回傳
        return XkBeanUtils.copyProperties(savedBO, AdmSystemResponse::new);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<AdmSystemResponse> createSampleSystems() {
        log.info(" [UseCase] 開始批量創建 Sample Systems...");

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));

        List<AdmSystemInitBO> systems = new ArrayList<>(Arrays.asList(
            new AdmSystemInitBO("system restAPI", now, "ADM", "管理維護系統", "Administrator System", true),
            new AdmSystemInitBO("system restAPI", now, "UPMS", "用戶權限管理系統", "User Permission Management System", true),
            new AdmSystemInitBO("system restAPI", now, "CMS", "內容管理系統", "Content Management System", true),
            new AdmSystemInitBO("system restAPI", now, "HRMS", "人力資源管理系統", "Human Resource Management System", true),
            new AdmSystemInitBO("system restAPI", now, "ERP", "企業資源管理系統", "Enterprise Resource Planning", true)
        ));

        log.info(" [UseCase] 即將存入 {} 筆系統資料", systems.size());
        List<AdmSystemBO> boList = admSystemService.saveAll(systems);
        log.info(" [UseCase] Sample Systems 創建完成，共存入 {} 筆資料", boList.size());

        return XkBeanUtils.copyListProperties(boList, AdmSystemResponse::new);
    }
	
}
