package com.xk.upms.application.usecase.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsUserFindRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;
import com.xk.upms.application.usecase.UpmsUserFindUseCase;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.service.UpmsUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 📌 UpmsUserFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責使用者查詢**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UpmsUserFindUseCaseImpl implements UpmsUserFindUseCase {

	private final UpmsUserService upmsUserService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UpmsUserResponseDTO> getList(UpmsUserFindRequestDTO request) {
		log.info("📌 查詢所有使用者（條件查詢 + 分頁）: {}", request);
		List<UpmsUserBO> upmsUsers = upmsUserService.findAllLike(request.keyword(), request.enabled(), request.locked());
		return XkBeanUtils.copyListProperties(upmsUsers, UpmsUserResponseDTO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UpmsUserResponseDTO getByUuid(UUID uuid) {
		log.info("📌 查詢使用者 UUID: {}", uuid);
		// 🔥 查詢使用者，並進行身份驗證
		UpmsUserBO userBO = upmsUserService.findByUuid(uuid)
				.orElseThrow(() -> new EntityNotFoundException("使用者不存在: " + uuid));
		return XkBeanUtils.copyProperties(userBO, UpmsUserResponseDTO::new);
	}

}
