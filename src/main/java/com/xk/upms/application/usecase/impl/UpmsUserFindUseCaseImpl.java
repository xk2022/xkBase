package com.xk.upms.application.usecase.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsUserRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.UpmsUserFindUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsUserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsUserFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責使用者查詢**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsUserFindUseCaseImpl implements UpmsUserFindUseCase {

	private final UpmsUserService upmsUserService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UpmsUserResponseDTO> getList(UpmsUserRequestDTO request) {
		log.info("📌 查詢所有使用者（條件查詢 + 分頁）: {}", request);

		UpmsUserBO userBO = XkBeanUtils.copyProperties(request, UpmsUserBO::new);
		Sort sort = Sort.by(Sort.Order.asc("id"), Sort.Order.asc("username"));
		List<UpmsUserBO> upmsUsers = upmsUserService.findAll(userBO, sort);
		return XkBeanUtils.copyListProperties(upmsUsers, UpmsUserResponseDTO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UpmsUserResponseDTO getOneById(Long id) {
		log.info("📌 查詢使用者 ID: {}", id);
		// 🔥 查詢使用者，並進行身份驗證
		UpmsUserBO userBO = upmsUserService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("使用者不存在: " + id));

		if (userBO.isAdmin()) {
			log.info("✅ 使用者 {} 是管理員", userBO.getUsername());
		} else {
			log.info("🔹 使用者 {} 是一般使用者", userBO.getUsername());
		}
		return XkBeanUtils.copyProperties(userBO, UpmsUserResponseDTO::new);
	}

}
