package com.xk.tom.domain.service.impl;

import com.xk.tom.application.mapper.ImportOrderMapper;
import com.xk.tom.application.model.*;
import com.xk.tom.domain.dao.repository.ImportOrderRepository;
import com.xk.tom.domain.model.bo.ImportOrderBo;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImportOrderServiceImpl implements ImportOrderService {

    @Autowired
    private final ImportOrderRepository repository;
    @Autowired
    private ImportOrderMapper mapper;

    @Override
    public ImportOrderBo create(ImportOrderCreateCmd createData) {
        log.info(" [Service] 創建訂單: {}", createData);

        // 轉換為業務層物件（AdmSystem），轉換 BO -> Entity
        ImportOrderEntity entity = mapper.toCreateEntity(createData);
        // 設定初始狀態
        entity.initialize();
        var savedEntity = repository.save(entity);
        return mapper.toBo(savedEntity);
    }

    @Override
    public ImportOrderBo update(UUID uuid, ImportOrderUpdateCmd updateData) {
        log.info("[Service] 更新進口訂單 uuid={}", uuid);

        var existing = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Import order not found"));

        // ✅ 更新欄位 (用 Entity 邏輯封裝 update)
        existing.setShippingCompany(updateData.getShippingCompany());
        existing.setContainerNumber(updateData.getContainerNumber());
        existing.setUpdatedTime(ZonedDateTime.now());

        var saved = repository.save(existing);
        return mapper.toBo(saved);
    }

    @Override
    public ImportOrderBo findByUuid(UUID uuid) {
        log.info("[Service] 查詢進口訂單 uuid={}", uuid);

        return repository.findByUuid(uuid)
                .map(mapper::toBo)
                .orElseThrow(() -> new RuntimeException("Import order not found"));
    }

    @Override
    public List<ImportOrderBo> findAll() {
        log.info("[Service] 查詢所有進口訂單");
        return repository.findAll().stream()
                .map(mapper::toBo)
                .toList();
    }

    @Override
    public Page<ImportOrderBo> findAll(Pageable pageable) {
        log.info("[Service] 分頁查詢進口訂單");
        return repository.findAll(pageable)
                .map(mapper::toBo);
    }

    @Override
    public List<ImportOrderBo> findByCondition(ImportOrderQueryDto condition) {
        log.info("[Service] 條件查詢進口訂單 condition={}", condition);

        // 這裡可以用 Specification / QueryDSL / 自訂 JPQL
        return repository.findByStatus(OrderStatus.PENDING).stream()
                .map(mapper::toBo)
                .toList();
    }

    @Override
    public ImportOrderBo assign(UUID uuid, OrderAssignCmd cmd) {
        ImportOrderEntity entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("訂單不存在"));

        entity.assign(cmd.getVehicleId(), cmd.getDriverId(), cmd.getAssignedBy(), LocalDate.now());

        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public ImportOrderBo updateStatus(UUID uuid, OrderUpdateStatusCmd cmd) {
        ImportOrderEntity entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("訂單不存在"));

        entity.updateStatus(cmd.getStatus(), cmd.getOperatorId(), cmd.getTimestamp());

        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public ImportOrderBo restore(UUID uuid) {
        ImportOrderEntity entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("訂單不存在"));

        entity.restore();

        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        ImportOrderEntity entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("找不到訂單: " + uuid));

        entity.delete(); // ✅ 交給 Entity 處理狀態檢查與刪除行為

        repository.save(entity);
    }

}
