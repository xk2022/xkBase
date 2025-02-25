package com.xk.adm.domain.model.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * 📌 `AdmSystemBO` - 負責系統管理的業務邏輯物件（Business Object）
 * 
 * - **主要角色**：
 *   - 負責業務層的邏輯處理，與 `Service` 互動
 *   - **不直接暴露給 Controller 層**
 *   - 透過 `DTO` 進行資料交換，與 `PO` 進行持久化映射
 * 
 * - **與其他層的關係**：
 *   - `Controller` → **DTO**（請求/回應）
 *   - `Service` → **BO**（業務處理）
 *   - `Repository` → **PO**（資料庫操作）
 * 
 * - **使用場景**：
 *   - 在 `AdmSystemService` 內部使用，封裝業務邏輯
 *   - 提供轉換方法，便於 DTO/PO 互相轉換
 * 
 * @author yuan Created on 2025/02/25.
 */
@Getter
@Setter
public class AdmSystemBO {
    private String uuid;         // 系統唯一識別碼
    private String code;       // 系統代碼
    private String name;       // 系統名稱
    private String description; // 系統描述
    private Boolean enabled;   // 啟用狀態
}
