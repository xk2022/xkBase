package com.xk.exapmleFolder.domain.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xk.exapmleFolder.domain.model.demo.DemoAggregate;

/**
 * 📌 DemoJpaRepository - Spring Data JPA 訂單倉儲介面
 *
 * - **支援客戶 ID 查詢**
 * - **支援分頁查詢**
 * 
 * ❌ 不需要 `@Repository`，因為 `JpaRepository` 會自動被 Spring 管理。
 * ❌ 不需要 `Serializable`，因為 `JpaRepository` 內部已經處理序列化。
 * ✅ `Page<DemoAggregate>` **優化查詢效能**
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 - 改進查詢效能 & 分頁處理
 */
public interface DemoJpaRepository extends JpaRepository<DemoAggregate, Long> {

    /**
     * 📌 透過 `customerId` 查詢訂單（不分頁）
     * 
     * List<DemoAggregate> orders = demoJpaRepository.findByCustomerId("C001");
     */
    List<DemoAggregate> findByCustomerId(String customerId);

    /**
     * 📌 透過 `customerId` 查詢訂單（支援分頁）
     * ✅ **建議使用 `Pageable` 提高查詢效能**
     * 
     * Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
     * Page<DemoAggregate> orders = demoJpaRepository.findByCustomerId("C001", pageable);
     */
    Page<DemoAggregate> findByCustomerId(String customerId, Pageable pageable);

}
