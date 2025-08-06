package com.xk.tom.infra.persistence.repository;

import com.xk.tom.application.model.ImportOrderQueryDto;
import com.xk.tom.domain.dao.repository.ImportOrderRepository;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.infra.persistence.mapper.OrderPersistenceMapper;
import com.xk.tom.infra.persistence.model.po.ImportOrderPo;
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
 * 📌 ImportOrderRepositoryImpl
 * - 實作 ImportOrderRepository
 * - 負責 ImportOrderEntity ↔ ImportOrderPo 的轉換
 *
 * @author yuan Created on 2025/08/05.
 */
@Repository
@RequiredArgsConstructor
public class ImportOrderRepositoryImpl implements ImportOrderRepository {

    private final SpringDataImportOrderJpaRepository importJpaRepo;
    private final OrderPersistenceMapper mapper;
    @PersistenceContext
    private EntityManager em;

    @Override
    public ImportOrderEntity save(ImportOrderEntity entity) {
        ImportOrderPo po = mapper.toPo(entity);
        ImportOrderPo saved = importJpaRepo.save(po);
        return mapper.toEntity(saved);
    }

    @Override
    public Optional<ImportOrderEntity> findByUuid(UUID uuid) {
        return importJpaRepo.findByUuid(uuid).map(mapper::toEntity);
    }

    @Override
    public List<ImportOrderEntity> findByStatus(OrderStatus status) {
        return importJpaRepo.findByStatus(status).stream().map(mapper::toEntity).toList();
    }

    @Override
    public List<ImportOrderEntity> findAll() {
        return importJpaRepo.findAll().stream().map(mapper::toEntity).toList();
    }

    @Override
    public Page<ImportOrderEntity> findAll(Pageable pageable) {
        return importJpaRepo.findAll(pageable)
                .map(mapper::toEntity);
    }

    @Override
    public List<ImportOrderEntity> findByCondition(ImportOrderQueryDto condition) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ImportOrderPo> query = cb.createQuery(ImportOrderPo.class);
        Root<ImportOrderPo> root = query.from(ImportOrderPo.class);

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

        TypedQuery<ImportOrderPo> typedQuery = em.createQuery(query);
        List<ImportOrderPo> resultList = typedQuery.getResultList();

        // 轉換為 Entity 回傳
        return resultList.stream()
                .map(mapper::toEntity)
                .toList();
    }

}
