package com.xk.adm.domain.service.impl;

import com.xk.adm.application.mapper.DictCategoryMapper;
import com.xk.adm.domain.dao.repository.DictCategoryRepository;
import com.xk.adm.domain.model.bo.DictCategoryBO;
import com.xk.adm.domain.model.entity.DictCategoryEntity;
import com.xk.adm.domain.model.po.DictCategoryPO;
import com.xk.adm.domain.service.DictCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `DictCategoryServiceImpl`
 *
 * - `DictCategoryService` 介面的實作
 * - 負責 `DictCategory`（管理系統）的創建、更新、刪除、查詢業務邏輯
 * - 透過 `DictCategoryRepository` 存取數據
 * - 內建軟刪除機制
 *
 * @author hank Created on 2025/09/03.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DictCategoryServiceImpl implements DictCategoryService {

    private final DictCategoryRepository repository;
    private final DictCategoryMapper mapper;

    @Override
    public DictCategoryBO create(DictCategoryEntity entity) {
        log.info("[Service] 建立選單類別");
        var po = mapper.toPO(entity);
        DictCategoryPO saved = repository.save(po);

        return mapper.toBo(saved);
    }

    @Override
    public DictCategoryBO update(UUID uuid, DictCategoryEntity entity) {
        log.info("[Service] 更新選單類別 uuid={} , entity={}" ,uuid,entity);
        var existing = repository.findById(uuid).orElseThrow(
                ()->new IllegalArgumentException("選單類別不存在" +uuid)
        );
        existing.setCode(entity.getCode());
        existing.setName(entity.getName());
        existing.setDescription(entity.getDescription());

        return mapper.toBo(existing);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除選單類別");
        var po = repository.findById(uuid).orElseThrow(
                ()->new IllegalArgumentException("選單類別不存在"+uuid)
        );
        //TODO 選單子項目也要刪除

        repository.delete(po);
    }

    @Override
    public DictCategoryBO findByCode(String code) {
        DictCategoryPO po = repository.findByCode(code);
        return  mapper.toBo(po);
    }
}
