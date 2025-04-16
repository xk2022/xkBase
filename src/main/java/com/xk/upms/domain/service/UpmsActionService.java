package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsAction;

import java.util.List;

/**
 * 📌 `UpmsActionService` - 使用者領域服務
 * 
 * - **負責處理 action 領域內的商業邏輯**
 * 
 * @author hank Created on 2025/02/21.
 * @author hank Updated on 2025/02/21
 */
public interface UpmsActionService {

    List<UpmsAction> findAll();

}
