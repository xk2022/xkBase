package com.xk.upms.application.usecase.impl;

import org.springframework.stereotype.Service;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.application.model.UpmsActionResponseDTO;
import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.application.usecase.UpmsActionFindUseCase;
import com.xk.upms.domain.model.bo.UpmsActionBO;
import com.xk.upms.domain.service.UpmsActionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 UpmsActionFindUseCaseImpl（應用層 Use Case 實作）
 * 
 * - **負責處理動作查詢邏輯**
 * 
 * @author hank Created on 2025/02/21.
 * @author hank Updated on 2025/02/21 something note here.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsActionFindUseCaseImpl implements UpmsActionFindUseCase {
	
	private final UpmsActionService upmsActionService;
	
	@Override
	public UpmsActionResponseDTO findById(Long actionId) {
		UpmsActionBO actionBO = upmsActionService.findById(actionId).orElseThrow(()-> new EntityNotFoundException("此動作不存在"+actionId));
		
		return XkBeanUtils.copyProperties(actionBO, UpmsActionResponseDTO::new);
	}

}
