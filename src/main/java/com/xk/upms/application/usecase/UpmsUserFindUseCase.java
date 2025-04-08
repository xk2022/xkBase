package com.xk.upms.application.usecase;

import java.util.List;

import com.xk.upms.application.model.UpmsUserFindRequestDTO;
import com.xk.upms.application.model.UpmsUserResponseDTO;

/**
 * 📌 UpmsUserFindUseCase（應用層 Use Case 介面）
 * 
 * - **提供使用者查找功能**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsUserFindUseCase {

	/**
	 * 📌 查詢所有使用者（支援分頁）
	 * 
	 * @param pageable 分頁請求
	 * @return 分頁使用者列表
	 */
//	Page<ExampleResponseDTO> getList(ExampleRequestDTO request, Pageable pageable);
	List<UpmsUserResponseDTO> getList(UpmsUserFindRequestDTO request);

	UpmsUserResponseDTO getOneById(Long id);

}
