package com.xk.exapmleFolder.application.usecase;

import com.xk.exapmleFolder.application.model.SaveUserCmd;
import com.xk.exapmleFolder.application.model.UserCreateRequest;

/**
 * 📌 `UserCreateUseCase` - 負責「新增使用者」的應用邏輯
 *
 * - 接收來自 Controller 的 {@link UserCreateRequest}
 * - 驗證輸入資料
 * - 呼叫領域層（Domain Service / Repository）執行「新增使用者」邏輯
 * - 回傳執行結果（例如：成功 / 失敗、或是新建的使用者資訊）
 *
 * 👉 **注意：**
 * - Controller 不應該知道內部業務規則
 * - UseCase 負責橋接 **應用層與領域層**
 *
 * @author yuan Created on 2025/07/31.
 * @author yuan Updated on 2025/07/31. something note here.
 */
public interface UserCreateUseCase {

    /**
     * 📌 執行「新增使用者」流程
     *
     * @param saveUserCmd 使用者建立請求 DTO（包含使用者基本資料，如帳號、名稱、Email 等）
     * @return （未來可擴充為 Response DTO，包含使用者 ID、建立時間等資訊）
     */
    void execute(SaveUserCmd saveUserCmd);

}

