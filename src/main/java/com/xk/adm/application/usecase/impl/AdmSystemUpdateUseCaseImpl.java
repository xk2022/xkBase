package com.xk.adm.application.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.adm.application.dto.AdmSystemRequest;
import com.xk.adm.application.dto.AdmSystemResponse;
import com.xk.adm.application.usecase.AdmSystemUpdateUseCase;
import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.bo.AdmSystemUpdateBO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.util.XkBeanUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemUpdateUseCaseImpl`
 * 
 * - 負責 `AdmSystem` 的更新操作
 * - **禁止** DTO 直接傳遞到 `domain.service`
 * - **使用 `AdmSystemBO` 來執行業務邏輯**
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemUpdateUseCaseImpl implements AdmSystemUpdateUseCase {

    private final AdmSystemService admSystemService;

    /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdmSystemResponse update(UUID uuid, AdmSystemRequest updateData) {
        log.info(" [UseCase] 更新系統資訊: uuid={}, request={}", uuid, updateData);

        // 直接委派給 Service 層
        AdmSystemUpdateBO updateBO = XkBeanUtils.copyProperties(updateData, AdmSystemUpdateBO::new);
        AdmSystemBO updatedBO = admSystemService.update(uuid, updateBO);

        // 轉換 BO -> DTO 回傳
        return XkBeanUtils.copyProperties(updatedBO, AdmSystemResponse::new);
    }
}
