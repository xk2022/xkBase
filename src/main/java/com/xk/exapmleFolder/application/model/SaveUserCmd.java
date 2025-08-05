package com.xk.exapmleFolder.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 📌 SaveUserCmd - Application → Domain 的輸入命令物件
 *
 * - 用於「新增 / 更新使用者」的 UseCase
 * - 與 Entity 區隔，專注於承載輸入資料
 * - 不包含業務邏輯
 *
 * 👉 UseCase 呼叫 DomainService 時會傳遞此 Cmd
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserCmd {

    /** 使用者 ID（更新時才需要，新增可為 null） */
    private Long id;

    /** 使用者名稱 */
    private String userName;

    /** 使用者 Email */
    private String email;

    /** 使用者密碼（尚未 hash，Domain Service 負責處理） */
    private String rawPassword;

}