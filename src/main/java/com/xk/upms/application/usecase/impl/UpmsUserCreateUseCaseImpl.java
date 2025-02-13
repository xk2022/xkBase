package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
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

	private final UpmsUserService upmsUserService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUserResponseDTO create(UpmsUserCreateDTO request) {
		log.info("📌 開始創建新使用者: {}", request.username());
		// ✅ 轉換 DTO -> BO
		UpmsUserBO userBO = XkBeanUtils.copyProperties(request, UpmsUserBO::new);
		// ✅ 儲存到 DB
		UpmsUserBO savedUser = upmsUserService.save(userBO);
		// ✅ 轉換 PO -> DTO 回傳
		return XkBeanUtils.copyProperties(savedUser, UpmsUserResponseDTO::new);
	}

}
