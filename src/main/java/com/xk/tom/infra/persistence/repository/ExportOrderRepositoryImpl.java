package com.xk.tom.infra.persistence.repository;

import com.xk.tom.application.model.ExportOrderQueryDto;
import com.xk.tom.domain.dao.repository.ExportOrderRepository;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.infra.persistence.mapper.OrderPersistenceMapper;
import com.xk.tom.infra.persistence.model.po.ExportOrderPo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    @PersistenceContext
    private EntityManager em;

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

    @Override
    public List<ExportOrderEntity> findAll() {
        return exportJpaRepo.findAll().stream().map(mapper::toEntity).toList();
    }

    @Override
    public Page<ExportOrderEntity> findAll(Pageable pageable) {
        return exportJpaRepo.findAll(pageable)
                .map(mapper::toEntity);
    }

    @Override
    public List<ExportOrderEntity> findByCondition(ExportOrderQueryDto condition) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExportOrderPo> query = cb.createQuery(ExportOrderPo.class);
        Root<ExportOrderPo> root = query.from(ExportOrderPo.class);

        List<Predicate> predicates = new ArrayList<>();

        if (condition.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), condition.getStatus()));
        }

        if (condition.getCustomerId() != null) {
            predicates.add(cb.equal(root.get("customerId"), condition.getCustomerId()));
        }

        if (condition.getStartDate() != null && condition.getEndDate() != null) {
            predicates.add(cb.between(root.get("shipDate"), condition.getStartDate(), condition.getEndDate()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<ExportOrderPo> typedQuery = em.createQuery(query);
        List<ExportOrderPo> resultList = typedQuery.getResultList();

        // 轉換為 Entity 回傳
        return resultList.stream()
                .map(mapper::toEntity)
                .toList();
    }

}
