package com.xk.adm.domain.service.impl;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xk.adm.domain.dao.repository.AdmSystemRepository;
import com.xk.adm.domain.model.bo.AdmSystemBO;
import com.xk.adm.domain.model.bo.AdmSystemInitBO;
import com.xk.adm.domain.model.po.AdmSystem;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.util.XkBeanUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemServiceImpl`
 * 
 * - `AdmSystemService` 介面的實作 - 負責 `AdmSystem`（管理系統）的創建、更新、刪除、查詢業務邏輯 - 透過
 * `AdmSystemRepository` 存取數據 - 內建軟刪除機制
 * 
 * @author yuan Created on 2025/02/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdmSystemServiceImpl implements AdmSystemService {

	private final AdmSystemRepository admSystemRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<AdmSystemBO> saveAllSystems(List<AdmSystemInitBO> boList) {
        if (boList == null || boList.isEmpty()) {
            log.warn("⚠️ 列表為空，不進行任何儲存操作");
            return Collections.emptyList();
        }
        
        List<AdmSystem> systems = XkBeanUtils.copyListProperties(boList, AdmSystem::new);
        List<AdmSystem> savedSystems = admSystemRepository.saveAll(systems);
        return XkBeanUtils.copyListProperties(savedSystems, AdmSystemBO::new);
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdmSystemBO> getAllSystems() {
		log.info("📌 獲取所有有效的系統列表");
//        List<AdmSystem> systems = admSystemRepository.findByDeletedFalse();
		List<AdmSystem> systems = admSystemRepository.findAll();
		return XkBeanUtils.copyListProperties(systems, AdmSystemBO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdmSystemBO> searchSystems(AdmSystemBO request) {
		log.info("📌 條件搜尋系統: {}", request);

		Example<AdmSystem> example = buildExample(request);
		List<AdmSystem> results = admSystemRepository.findAll(example);
		return XkBeanUtils.copyListProperties(results, AdmSystemBO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<AdmSystemBO> findById(String uuid) {
		log.info("📌 查找系統: uuid={}", uuid);
		
		UUID systemUuid = UUID.fromString(uuid); // ✅ 轉換 String -> UUID
		
		return admSystemRepository.findById(systemUuid).map(
				system -> XkBeanUtils.copyProperties(system, AdmSystemBO::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<AdmSystemBO> getSystemByCode(String code) {
		log.info("📌 根據 Code 查詢系統: {}", code);
		return admSystemRepository.findByCode(code).map(
				entity -> XkBeanUtils.copyProperties(entity, AdmSystemBO::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public AdmSystemBO createSystem(AdmSystemBO systemBO) {
		log.info("📌 創建新系統: {}", systemBO.getName());
		AdmSystem entity = XkBeanUtils.copyProperties(systemBO, AdmSystem::new);
		entity.setUuid(null); // 確保 UUID 由 DB 生成
		AdmSystem savedEntity = admSystemRepository.save(entity);
		return XkBeanUtils.copyProperties(savedEntity, AdmSystemBO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public AdmSystemBO update(AdmSystemBO updateData) {
	    log.info("📌 更新系統: uuid={}", updateData.getUuid());

	    UUID systemUuid = UUID.fromString(updateData.getUuid()); // ✅ 確保轉換 UUID
	    AdmSystem existingSystem = admSystemRepository.findById(systemUuid)
	            .orElseThrow(() -> new EntityNotFoundException("系統 ID " + updateData.getUuid() + " 不存在"));

	    // 只更新需要變更的欄位，而不是直接覆蓋整個物件
	    existingSystem.setName(updateData.getName());
	    existingSystem.setDescription(updateData.getDescription());
	    existingSystem.setUpdatedTime(ZonedDateTime.now());

	    AdmSystem savedPO = admSystemRepository.save(existingSystem);
	    return XkBeanUtils.copyProperties(savedPO, AdmSystemBO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Boolean softDeleteSystem(String uuid) {
		log.info("📌 嘗試刪除系統: uuid={}", uuid);

		UUID systemUuid = UUID.fromString(uuid); // ✅ 轉換 String -> UUID

		AdmSystem system = admSystemRepository.findById(systemUuid)
				.orElseThrow(() -> new EntityNotFoundException("系統 ID " + uuid + " 不存在"));

		system.setDeleted(true);
		system.setDeletedTime(ZonedDateTime.now());
		admSystemRepository.save(system);

		log.info("✅ 系統已成功軟刪除: uuid={}", uuid);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean restoreSystem(String uuid) {

		UUID systemUuid = UUID.fromString(uuid); // ✅ 轉換 String -> UUID

		Optional<AdmSystem> systemOpt = admSystemRepository.findById(systemUuid);
		if (systemOpt.isPresent()) {
			AdmSystem system = systemOpt.get();
			system.setDeleted(false);
			system.setDeletedTime(null);
			admSystemRepository.save(system);
			return true;
		}
		return false;
	}

	private Example<AdmSystem> buildExample(AdmSystemBO request) {
	    ExampleMatcher matcher = ExampleMatcher.matching()
	        .withIgnoreNullValues() // ✅ 確保 null 值不影響查詢條件
	        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
	        .withIgnoreCase();

	    return Example.of(XkBeanUtils.copyProperties(request, AdmSystem::new), matcher);
	}
	
}
