package com.xk.adm.domain.service.impl;

import com.xk.adm.application.converter.DictItemConverter;
import com.xk.adm.domain.dao.mapper.DictItemMapper;
import com.xk.adm.domain.dao.repository.DictItemRepository;
import com.xk.adm.domain.model.bo.DictItemBO;
import com.xk.adm.domain.model.entity.DictItemEntity;
import com.xk.adm.domain.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `DictItemServiceImpl`
 *
 * - `DictItemService` 介面的實作
 * - 負責 `DictItem`（選單項目系統）的創建、更新、刪除、查詢業務邏輯
 * - 透過 `DictItemRepository` 存取數據
 * - 內建軟刪除機制
 *
 * @author hank Created on 2025/09/10.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DictItemServiceImpl implements DictItemService {

    private final DictItemConverter convert;
    private final DictItemMapper mapper;
    private final DictItemRepository repository;

    @Override
    public DictItemBO findByItemCodeAndDeleted0(String itemCode) {
        return null;
    }

    @Override
    public DictItemBO create(DictItemEntity entity) {
        log.info("[Service] 建立選單項目 entity={}"  ,entity);
        var po = convert.toPo(entity);
        var saved = repository.save(po);
        return convert.toBo(saved);
    }

    @Override
    public DictItemBO update(DictItemBO dictItemBO, DictItemEntity entity) {
        log.info("[Service] 更新選單項目 dictItem=｛} , entity={}" ,dictItemBO ,entity);
        dictItemBO.setItemCode(entity.getItemCode());
        dictItemBO.setItemName(entity.getItemName());
        dictItemBO.setCatrgoryCode(entity.getCategoryCode());

        var po = convert.BOtoPO(dictItemBO);
        var saved = repository.save(po);

        return convert.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) throws NotFoundException {
        log.info("[Service] 刪除選單項目 uuid={}", uuid);
        var po = repository.findById(uuid).orElseThrow(
                ()-> new NotFoundException("該選單項目不存在")
        );

        repository.delete(po);

    }
}
