package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ImportOrderQueryDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.domain.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 📌 ImportOrderFindUseCase
 * - 查詢進口訂單的應用層用例 (Application Layer)
 * <p>
 * 📌 功能：
 * - 根據 UUID 查詢單筆進口訂單
 * - 根據狀態查詢進口訂單
 * - 查詢所有進口訂單
 * <p>
 * ⚠️ 注意：
 * - Controller 僅依賴此 UseCase，不直接依賴 Repository
 * - Mapper 負責 Entity ↔ ResponseDto 的轉換
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ImportOrderFindUseCase {

    // 單筆查詢
    /**
     * 流程：
     * Controller 呼叫 UseCase (帶入 uuid)
     * UseCase 呼叫 Repository → importOrderRepository.findByUuid(uuid)
     * 如果找不到 → 拋出例外
     * Mapper → entity → responseDto
     * 回傳 ResponseDto
     */
    OrderResponseDto getByUuid(UUID uuid);
    // TODO - LESS USE
//    OrderResponseDto getById(Long id);

    // 條件查詢
    // TODO - USE findByCondition
//    List<OrderResponseDto> getByStatus(OrderStatus status);
//    List<OrderResponseDto> getByCustomerId(String customerId);
//    List<OrderResponseDto> getByDateRange(LocalDate start, LocalDate end);
    // TODO - LESS USE
//    List<OrderResponseDto> searchByKeyword(String keyword);

    // 列表查詢
    /**
     * 查詢所有進口訂單
     *
     * @return 全部進口訂單清單
     */
    List<OrderResponseDto> getAll();
    Page<OrderResponseDto> getAll(Pageable pageable);
    // TODO - LESS USE
//    List<OrderResponseDto> getRecent(int limit);

    // 複合查詢
    List<OrderResponseDto> findByCondition(ImportOrderQueryDto query);

    // 統計
    // TODO - LESS USE
//    long countByStatus(OrderStatus status);
//    long countByCustomer(String customerId);
}
