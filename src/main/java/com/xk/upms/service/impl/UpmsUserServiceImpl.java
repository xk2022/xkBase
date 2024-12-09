package com.xk.upms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.common.util.XkBeanUtils;
import com.xk.common.util.XkGenericUpdateService;
import com.xk.upms.dao.repository.UpmsUserRepository;
import com.xk.upms.model.bo.UpmsUserReq;
import com.xk.upms.model.bo.UpmsUserSaveReq;
import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.model.vo.UpmsUserResp;
import com.xk.upms.service.UpmsUserService;

import jakarta.persistence.EntityNotFoundException;

/**
 * Implementation of the {@link UpmsUserService} interface.
 * <p>
 * This class provides concrete implementations for managing UPMS users,
 * including creating, retrieving, updating, and deleting user records.
 * </p>
 *
 * @author yuan
 * @version 1.1, Created on 2024/12/06.
 */
@Service
public class UpmsUserServiceImpl implements UpmsUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

	@Autowired
	private UpmsUserRepository upmsUserRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UpmsUserResp> getList(UpmsUserReq resources) {
		// 設置 Example 條件，如果 resources 為 null 則返回 null
		Example<UpmsUser> example = resources == null ? null
				: Example.of(XkBeanUtils.copyProperties(resources, UpmsUser::new),
						ExampleMatcher.matching().withIgnoreNullValues()
								.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase());
		// 提取排序規則，避免重複定義
		Sort sort = Sort.by(Sort.Order.asc("id"), Sort.Order.asc("name"));

		// 記錄查詢條件和排序規則
		LOGGER.info("查詢 UpmsUser，條件: {}", resources);
		LOGGER.info("排序規則: {}", sort);

		// 查詢數據並轉換為 UpmsOrganizationResp 列表
		List<UpmsUser> users = (example == null) ? upmsUserRepository.findAll(sort)
				: upmsUserRepository.findAll(example, sort);

		// 記錄查詢結果數量（僅在 DEBUG 級別下）
		LOGGER.info("查詢結果數量: {}", users.size());

		return XkBeanUtils.copyListProperties(users, UpmsUserResp::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public UpmsUserResp getOneById(Long id) {
		UpmsUser entity = upmsUserRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("UpmsUser with ID " + id + " not found"));
		return XkBeanUtils.copyProperties(entity, UpmsUserResp::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUserResp create(UpmsUserSaveReq resources) {
		// 創建 Entity 實體並複製屬性
		UpmsUser reqEntity = XkBeanUtils.copyProperties(resources, UpmsUser::new);
		// 保存實體到數據庫
		UpmsUser savedEntity = upmsUserRepository.save(reqEntity);
		// 創建並返回 UpmsOrganizationSaveResp
		return XkBeanUtils.copyProperties(savedEntity, UpmsUserResp::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUserResp update(Long id, UpmsUserSaveReq resources) {
		// 查找指定 ID 的 UpmsOrganization，如果不存在則拋出異常
		UpmsUser entity = upmsUserRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Entity<UpmsOrganization> with ID " + id + " not found"));
		// 使用通用更新服務更新實體
		XkGenericUpdateService<UpmsUser> updateService = new XkGenericUpdateService<>();
		UpmsUser updatedEntity = updateService.updateEntity(entity, resources);
		// 保存更新後的實體
		UpmsUser savedEntity = upmsUserRepository.save(updatedEntity);
		// 創建並返回 UpmsOrganizationSaveResp
		return XkBeanUtils.copyProperties(savedEntity, UpmsUserResp::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteUser(Long id) {
		if (upmsUserRepository.existsById(id)) {
			upmsUserRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
