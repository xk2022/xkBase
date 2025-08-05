package com.xk.exapmleFolder.domain.service;

import com.xk.exapmleFolder.domain.model.UserBO;
import com.xk.exapmleFolder.domain.model.UserEntity;

/**
 * 📌 `UserDomainService` - 專責使用者領域邏輯
 *
 * - 適合放置跨多個實體的商業規則
 */
public interface UserService {

    /**
     * 📌 創建或更新使用者（回傳 `Optional<T>` 以避免 `null`）
     *
     * @param user 使用者物件
     * @return 儲存後的使用者資訊
     */
    UserBO save(UserBO user);

    void createUser(UserEntity createEntity);
}