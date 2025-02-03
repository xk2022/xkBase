package com.xk.upms.application.usecase.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.model.example.EmailVO;
import com.xk.upms.application.model.UpmsUserCreateDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.UpmsUserCreateUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UserCreateUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理使用者創建邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsUserCreateUseCaseImpl implements UpmsUserCreateUseCase {

	private UpmsUserService upmsUserService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUserResponseDTO create(UpmsUserCreateDTO request) {
		log.info("📌 開始創建新使用者: {}", request.getUsername());
		// ✅ 轉換 DTO -> BO
		UpmsUserBO userBO = XkBeanUtils.copyProperties(request, UpmsUserBO::new);
		// ✅ 手動處理 EmailVO 轉換
//        userBO.setEmail(new EmailVO(request.getEmail())); // ❗手動轉換 EmailVO
		// ✅ 執行業務邏輯（如 Email 檢查）
		if (!new EmailVO(userBO.getEmail()).isValid()) {
			throw new IllegalArgumentException("無效的 Email 格式");
		}
		// ✅ 儲存到 DB
		UpmsUserBO savedUser = upmsUserService.save(userBO);
		// ✅ 轉換 PO -> DTO 回傳
		return XkBeanUtils.copyProperties(savedUser, UpmsUserResponseDTO::new);
	}

}
