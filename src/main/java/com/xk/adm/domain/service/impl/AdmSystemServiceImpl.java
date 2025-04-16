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
import com.xk.adm.domain.model.bo.AdmSystemCreateBO;
import com.xk.adm.domain.model.bo.AdmSystemInitBO;
import com.xk.adm.domain.model.bo.AdmSystemSearchBO;
import com.xk.adm.domain.model.bo.AdmSystemUpdateBO;
import com.xk.adm.domain.model.entity.AdmSystem;
import com.xk.adm.domain.model.po.AdmSystemPO;
import com.xk.adm.domain.service.AdmSystemService;
import com.xk.common.handler.EntityAlreadyDeletedException;
import com.xk.common.util.GenericUpdateService;
import com.xk.common.util.XkBeanUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmSystemServiceImpl`
 * 
 * - `AdmSystemService` 介面的實作 
 * - 負責 `AdmSystem`（管理系統）的創建、更新、刪除、查詢業務邏輯 
 * - 透過 `AdmSystemRepository` 存取數據 
 * - 內建軟刪除機制
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
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AdmSystemBO create(AdmSystemCreateBO createData) {
        log.info(" [Service] 創建系統: {}", createData.getName());

	    // 轉換為業務層物件（AdmSystem），轉換 BO -> Entity
	    AdmSystem entity = XkBeanUtils.copyProperties(createData, AdmSystem::new);
	    entity.initialize(); // 設定初始狀態
	    // 存入資料庫（Entity -> PO）
	    AdmSystemPO savedPO = admSystemRepository.save(entity.toPO());
	    // 轉換回 BO 回傳（PO -> BO）
	    return savedPO.toBO();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<AdmSystemBO> saveAll(List<AdmSystemInitBO> boList) {
		if (boList == null || boList.isEmpty()) {
			log.warn("⚠️ 列表為空，不進行任何儲存操作");
			return Collections.emptyList();
		}

		List<AdmSystemPO> systems = XkBeanUtils.copyListProperties(boList, AdmSystemPO::new);
		List<AdmSystemPO> savedSystems = admSystemRepository.saveAll(systems);
		return XkBeanUtils.copyListProperties(savedSystems, AdmSystemBO::new);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	@Override
	public Optional<AdmSystemBO> findByUuid(UUID uuid) {
		log.info(" [Service] 查找系統: uuid={}", uuid);
		return admSystemRepository.findByDeletedFalseAndUuid(uuid)
				.map(system -> XkBeanUtils.copyProperties(system, AdmSystemBO::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<AdmSystemBO> findByCode(String code) {
	    log.info(" [Service] 根據 Code 查詢系統: {}", code);
	    return admSystemRepository.findByCode(code)
	            .map(AdmSystemPO::toBO); // ✅ PO -> BO 轉換
	}

    /**
     * 檢查系統是否存在（根據 UUID）
     */
    @Override
    public boolean existsById(UUID uuid) {
        return admSystemRepository.existsById(uuid);
    }

    /**
     * 檢查系統代號是否存在（避免重複）
     */
    @Override
    public boolean existsByCode(String code) {
        return admSystemRepository.existsByCode(code);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdmSystemBO> findAll() {
		log.info(" [Service] 獲取所有有效的系統列表");
        List<AdmSystemPO> systems = admSystemRepository.findByDeletedFalse();
//		List<AdmSystemPO> systems = admSystemRepository.findAll();
		return XkBeanUtils.copyListProperties(systems, AdmSystemBO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AdmSystemBO> search(AdmSystemSearchBO searchParams) {
		log.info(" [Service] 條件搜尋系統: {}", searchParams);

	    Example<AdmSystemPO> example = buildExample(searchParams); // ✅ 確保是 PO
	    List<AdmSystemPO> results = admSystemRepository.findAll(example); // ✅ 確保 Repository 操作 PO
	    return XkBeanUtils.copyListProperties(results, AdmSystemBO::new); // ✅ PO -> BO 轉換
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public AdmSystemBO update(UUID uuid, AdmSystemUpdateBO updateData) {
	    log.info(" [Service] 更新系統 - ID: {}", uuid);

	    // 查詢系統是否存在
	    AdmSystemPO existingPO = admSystemRepository.findByDeletedFalseAndUuid(uuid)
	        .orElseThrow(() -> new EntityNotFoundException("❌ 找不到系統: " + uuid));
	    // 進行安全的動態更新（不覆蓋 null）
		GenericUpdateService<AdmSystemPO> updateSystemService = new GenericUpdateService<>();
		existingPO = updateSystemService.updateEntity(existingPO, updateData);
	    // 保存更新後的實體
	    AdmSystemPO updatedPO = admSystemRepository.save(existingPO);
	    log.info("✅ [Service] 更新成功 - ID: {}, Name: {}", updatedPO.getUuid(), updatedPO.getName());
	    // 轉換 PO -> BO 並回傳
	    return XkBeanUtils.copyProperties(updatedPO, AdmSystemBO::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void softDelete(UUID uuid) {
	    log.info(" [Service] 軟刪除系統 - ID: {}", uuid);

	    AdmSystemPO existingPO = admSystemRepository.findByDeletedFalseAndUuid(uuid)
	        .orElseThrow(() -> new EntityNotFoundException("❌ 找不到系統: " + uuid));
	    // 轉換 PO -> DO（領域對象）
	    AdmSystem systemDO = existingPO.toDomain();
	    if (systemDO.isDeleted()) {
	        log.warn("⚠️ [Service] 系統已被刪除 - ID: {}", uuid);
	        throw new EntityAlreadyDeletedException("⚠️ 系統已刪除: " + uuid);
	    }

	    systemDO.markAsDeleted(); // ✅ 在 Entity 層執行軟刪除
	    systemDO.setDeletedTime(ZonedDateTime.now());

		AdmSystemPO po = systemDO.toPO();
		admSystemRepository.save(po);
	    log.info("✅ [Service] 軟刪除成功 - ID: {}", uuid);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void restore(UUID uuid) {
	    log.info("️ [Service] 恢復已刪除系統 - ID: {}", uuid);

	    AdmSystemPO existingPO = admSystemRepository.findById(uuid)
	        .orElseThrow(() -> new EntityNotFoundException("❌ 找不到系統: " + uuid));

	    // 轉換 PO -> DO（領域對象）
	    AdmSystem systemDO = existingPO.toDomain();
	    if (!systemDO.isDeleted()) {
	        log.warn("⚠️ [Service] 系統未被刪除 - ID: {}", uuid);
	        return;
	    }
	    systemDO.setDeleted(false);
	    systemDO.setDeletedTime(null);

	    admSystemRepository.save(systemDO.toPO());
	    log.info("✅ [Service] 系統恢復成功 - ID: {}", uuid);
	}

	@Transactional
	@Override
	public void delete(UUID uuid) {
	    log.info(" [Service] 徹底刪除系統 - ID: {}", uuid);

	    AdmSystemPO existingPO = admSystemRepository.findById(uuid)
	        .orElseThrow(() -> new EntityNotFoundException("❌ 找不到系統: " + uuid));

	    admSystemRepository.delete(existingPO);
	    log.info("✅ [Service] 系統已徹底刪除 - ID: {}", uuid);
	}

	private Example<AdmSystemPO> buildExample(AdmSystemSearchBO searchParams) {
	    AdmSystemPO probe = new AdmSystemPO(); // 使用 PO 類別
	    probe.setCode(searchParams.getCode());
	    probe.setName(searchParams.getName());
	    probe.setDeleted(false); // 過濾已刪除的資料

	    ExampleMatcher matcher = ExampleMatcher.matching()
	            .withIgnoreNullValues() // 確保 null 值不影響查詢條件
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
	    return Example.of(probe, matcher);
	}

}
