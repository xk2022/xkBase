package com.xk.adm.application.usecase;

import com.xk.adm.application.dto.SystemConfigDTO;

/**
 * 📌 `SystemConfigUseCase` - 負責處理系統設定的業務邏輯
 * 
 * - 讀取當前系統設定
 * - 更新系統設定
 * 
 * @author yuan Created on 2025/02/24.
 */
public interface AdmSystemConfigUseCase {

    /**
     * 🔹 獲取當前系統設定
     * 
     * @return {@link SystemConfigDTO} 系統設定 DTO
     */
    SystemConfigDTO getSystemSettings();

    /**
     * 🔹 更新系統設定
     * 
     * @param config {@link SystemConfigDTO} 傳入更新的設定值
     * @return {@link SystemConfigDTO} 更新後的設定
     */
    SystemConfigDTO updateSettings(Long uuid, SystemConfigDTO request);

    SystemConfigDTO create();

}
