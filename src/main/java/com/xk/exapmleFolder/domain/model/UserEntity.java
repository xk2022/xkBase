package com.xk.exapmleFolder.domain.model;

import com.xk.exapmleFolder.domain.model.example.EmailVO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 📌 UserEntity - 使用者聚合根 (Aggregate Root)
 *
 * - 代表 Domain 中的「使用者」
 * - 擁有唯一 ID
 * - 屬性中可以組合 VO (值物件)
 * - 可包含業務規則 (方法)
 */
@Data
@Builder
@NoArgsConstructor      // ✅ for XkBeanUtils.copyProperties
@AllArgsConstructor
// ✅ for XkBeanUtils.copyProperties
public class UserEntity {

    /** 識別 ID */
    private Long id;

    /** 使用者名稱（值物件封裝） */
    private UserNameVO userName;

    /** 使用者 Email（值物件封裝） */
    private EmailVO email;

    /** 使用者密碼（值物件封裝，需經過 Hash） */
    private PasswordVO password;

    /** 建立時間 */
    private LocalDateTime createdAt;

    /** 最後更新時間 */
    private LocalDateTime updatedAt;

    // 📌 業務邏輯：修改使用者名稱
    public void changeUserName(UserNameVO newName) {
        if (newName == null) {
            throw new IllegalArgumentException("使用者名稱不可為空");
        }
        this.userName = newName;
        this.updatedAt = LocalDateTime.now();
    }

    // 📌 業務邏輯：修改 Email
    public void changeEmail(EmailVO newEmail) {
        if (newEmail == null || !newEmail.isValid()) {
            throw new IllegalArgumentException("Email 格式錯誤");
        }
        this.email = newEmail;
        this.updatedAt = LocalDateTime.now();
    }

    // 📌 業務邏輯：修改密碼
    public void changePassword(PasswordVO newPassword) {
        if (newPassword == null || !newPassword.isHashed()) {
            throw new IllegalArgumentException("密碼必須經過 Hash");
        }
        this.password = newPassword;
        this.updatedAt = LocalDateTime.now();
    }

}