package com.xk.tom.domain.service.impl;

import com.xk.tom.application.mapper.ImportOrderMapper;
import com.xk.tom.application.model.ImportOrderCmd;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.domain.dao.repository.ImportOrderRepository;
import com.xk.tom.domain.model.bo.ImportOrderBo;
import com.xk.tom.infrastructure.persistence.model.po.ImportOrderPo;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final ImportOrderRepository importOrderRepository;
    @Autowired
    private ImportOrderMapper mapper;

//    @Override
//    public ImportOrderBo create(ImportOrderCmd createData) {
//        log.info(" [Service] 創建訂單: {}", createData);
//
//        // 轉換為業務層物件（AdmSystem），轉換 BO -> Entity
//        ImportOrderEntity entity = mapper.toCreateEntity(createData);
//        entity.initialize(); // 設定初始狀態
//        // 存入資料庫（Entity -> PO）
//        ImportOrderPo savedPo = importOrderRepository.save(mapper.toPo(entity));
//        // 轉換回 BO 回傳（PO -> BO）
//        return mapper.toBo(savedPo);
//    }
//
//    @Override
//    public ImportOrderPo findByUuid(UUID uuid) {
//        return importOrderRepository.findByUuid(uuid)
//                .orElseThrow(() -> new RuntimeException("Import order not found"));
//    }
//
//    @Override
//    public List<ImportOrderPo> findByContainerNumber(String containerNumber) {
//        return importOrderRepository.findByContainerNumber(containerNumber);
//    }
//
//    @Override
//    public void delete(UUID uuid) {
//        ImportOrderPo order = findByUuid(uuid);
//        importOrderRepository.delete(order);
//    }

    @Override
    public ImportOrderBo create(ImportOrderCmd createData) {
        return null;
    }

    @Override
    public ImportOrderPo findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<ImportOrderPo> findByContainerNumber(String containerNumber) {
        return List.of();
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public ImportOrderEntity getOrder(UUID uuid) {
        return importOrderRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Import order not found"));
    }
}