package com.xk.adm.application.usecase.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.adm.application.usecase.AdmSystemDeleteUseCase;
import com.xk.adm.domain.service.AdmSystemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemDeleteUseCaseImpl`
 * 
 * - **負責刪除 `AdmSystem`** 
 * - **確保 `uuid` 存在後再刪除**
 * - **避免 `null` 值導致 `Exception`**
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemDeleteUseCaseImpl implements AdmSystemDeleteUseCase {

    private final AdmSystemService admSystemService;

    /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(UUID uuid) {
        log.info("📌 [UseCase] 刪除系統: {}", uuid);

        // 直接呼叫 Service，內部自行判斷是否存在
        admSystemService.softDelete(uuid);
    }
}
