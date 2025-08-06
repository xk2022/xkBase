package com.xk.tom.domain.service.impl;

import com.xk.tom.application.mapper.ExportOrderMapper;
import com.xk.tom.application.model.*;
import com.xk.tom.domain.dao.repository.ExportOrderRepository;
import com.xk.tom.domain.model.bo.ExportOrderBo;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.service.ExportOrderService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderServiceImpl
 * - 出口訂單 Service 實作
 * - 負責處理 ExportOrderEntity 的核心邏輯
 * <p>
 * Domain Service → Repository → Persistence
 *
 * @author yuan Created on 2025/08/05.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExportOrderServiceImpl implements ExportOrderService {

    private final ExportOrderRepository repository;
    private final ExportOrderMapper mapper;

    @Override
    public ExportOrderBo findByUuid(UUID uuid) {
        log.info("[Service] 查詢出口訂單 uuid={}", uuid);
        var entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("出口訂單不存在: " + uuid));
        return mapper.toBo(entity);
    }

    @Override
    public List<ExportOrderBo> findAll() {
        log.info("[Service] 查詢所有出口訂單");
        return repository.findAll().stream()
                .map(mapper::toBo)
                .toList();
    }

    @Override
    public Page<ExportOrderBo> findAll(Pageable pageable) {
        log.info("[Service] 分頁查詢出口訂單");
        return repository.findAll(pageable)
                .map(mapper::toBo);
    }

    @Override
    public List<ExportOrderBo> findByCondition(@NotNull ExportOrderQueryDto condition) {
        log.info("[Service] 條件查詢出口訂單 condition={}", condition);
        return repository.findByCondition(condition).stream()
                .map(mapper::toBo)
                .toList();
    }

    @Override
    public ExportOrderBo create(ExportOrderCreateCmd cmd) {
        log.info("[Service] 建立出口訂單 cmd={}", cmd);
        ExportOrderEntity entity = mapper.toEntity(cmd);
        entity.initialize(); // 初始化狀態 = PENDING
        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public ExportOrderBo update(UUID uuid, ExportOrderUpdateCmd updateData) {
        log.info("[Service] 更新出口訂單 uuid={}, cmd={}", uuid, updateData);
        var existing = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("出口訂單不存在: " + uuid));

        // ✅ 更新欄位 (保持業務規則檢查)
        existing.setShippingCompany(updateData.getShippingCompany());
        existing.setVesselVoyage(updateData.getVesselVoyage());
        existing.setContainerNumber(updateData.getContainerNumber());
        existing.setContainerType(updateData.getContainerType());
        existing.setPickupCode(updateData.getPickupCode());
        existing.setPickupYard(updateData.getPickupYard());
        existing.setDeliveryYard(updateData.getDeliveryYard());
        existing.setLoadingLocation(updateData.getLoadingLocation());
        existing.setLoadingDate(updateData.getLoadingDate());
        existing.setLoadingTime(updateData.getLoadingTime());
        existing.setRemark(updateData.getRemark());
        existing.setUpdatedTime(LocalDateTime.now());

        var saved = repository.save(existing);
        return mapper.toBo(saved);
    }

    @Override
    public ExportOrderBo assign(UUID uuid, OrderAssignCmd cmd) {
        log.info("[Service] 指派出口訂單 uuid={}, cmd={}", uuid, cmd);
        var entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("出口訂單不存在: " + uuid));
        entity.assign(cmd.getVehicleId(), cmd.getDriverId(), cmd.getAssignedBy(), cmd.getAssignedAt());
        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public ExportOrderBo updateStatus(UUID uuid, OrderUpdateStatusCmd cmd) {
        log.info("[Service] 更新出口訂單狀態 uuid={}, cmd={}", uuid, cmd);
        var entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("出口訂單不存在: " + uuid));
        entity.updateStatus(cmd.getNewStatus(), cmd.getOperatorId(), cmd.getTimestamp());
        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public ExportOrderBo restore(UUID uuid) {
        log.info("[Service] 恢復出口訂單 uuid={}", uuid);
        var entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("出口訂單不存在: " + uuid));
        entity.restore();
        var saved = repository.save(entity);
        return mapper.toBo(saved);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[Service] 刪除出口訂單 uuid={}", uuid);
        var entity = repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("出口訂單不存在: " + uuid));
        entity.delete();
        repository.save(entity);
    }

}
