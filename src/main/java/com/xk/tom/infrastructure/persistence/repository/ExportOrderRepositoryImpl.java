package com.xk.tom.infrastructure.persistence.repository;

import com.xk.tom.domain.dao.repository.ExportOrderRepository;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.infrastructure.persistence.mapper.OrderPersistenceMapper;
import com.xk.tom.infrastructure.persistence.model.po.ExportOrderPo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 ExportOrderRepositoryImpl
 * - 實作 ExportOrderRepository
 * - 負責 ExportOrderEntity ↔ ExportOrderPo 的轉換
 *
 * @author yuan Created on 2025/08/05.
 */
@Repository
@RequiredArgsConstructor
public class ExportOrderRepositoryImpl implements ExportOrderRepository {

    private final SpringDataExportOrderJpaRepository exportJpaRepo;
    private final OrderPersistenceMapper mapper;

    @Override
    public ExportOrderEntity save(ExportOrderEntity entity) {
        ExportOrderPo po = mapper.toPo(entity);
        ExportOrderPo saved = exportJpaRepo.save(po);
        return mapper.toEntity(saved);
    }

    @Override
    public Optional<ExportOrderEntity> findByUuid(UUID uuid) {
        return exportJpaRepo.findByUuid(uuid).map(mapper::toEntity);
    }

    @Override
    public List<ExportOrderEntity> findByStatus(OrderStatus status) {
        return exportJpaRepo.findByStatus(status).stream().map(mapper::toEntity).toList();
    }
}