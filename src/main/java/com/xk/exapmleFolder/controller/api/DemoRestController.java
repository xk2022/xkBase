package com.xk.exapmleFolder.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.exapmleFolder.application.model.DemoRequestDTO;
import com.xk.exapmleFolder.application.model.DemoResponseDTO;
import com.xk.exapmleFolder.application.usecase.DemoCancelUseCase;
import com.xk.exapmleFolder.application.usecase.DemoCreateUseCase;
import com.xk.exapmleFolder.application.usecase.DemoFindUseCase;
import com.xk.exapmleFolder.application.usecase.DemoUpdateUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 OrderController - 訂單管理 API
 * 
 * - 負責處理 API 端點
 * - 依照 DDD 分層設計，調用 UseCase 層
 * - 提供 CRUD 操作，支援分頁查詢
 * 
 * 設計如下，內容 Order replace named Demo
 * 📂 com.xk.order
 ├── 📂 api                // ✅ API 層（Controller）
 │   ├── OrderRestController.java
 │
 ├── 📂 application        // ✅ 應用層（Application Layer）
 │   ├── usecase          // **應用服務**
 │   │   ├── OrderCreateUseCase.java
 │   │   ├── OrderFindUseCase.java
 │   │   ├── OrderUpdateUseCase.java
 │   │   ├── OrderCancelUseCase.java
 │   ├── dto              // **DTO**
 │   │   ├── OrderRequestDTO.java
 │   │   ├── OrderResponseDTO.java
 │   ├── mapper           // **DTO 映射**
 │   │   ├── OrderMapper.java
 │
 ├── 📂 domain            // ✅ 領域層（Domain Layer）
 │   ├── model           // **聚合根 & 實體**
 │   │   ├── Order.java      // 聚合根（Aggregate Root）
 │   │   ├── OrderItem.java  // 訂單項目（Entity）
 │   │   ├── OrderStatus.java // 訂單狀態（ENUM）
 │   ├── repository      // **倉儲（Repository）**
 │   │   ├── OrderRepository.java
 │   ├── service        // **領域服務**
 │   │   ├── OrderDomainService.java
 │   ├── event         // **事件（Event）**
 │   │   ├── OrderPlacedEvent.java
 │
 ├── 📂 infrastructure     // ✅ 基礎設施層（Infrastructure Layer）
 │   ├── repository.jpa  // **JPA Repository**
 │   │   ├── OrderJpaRepository.java
 │   ├── event.handler  // **事件處理**
 │   │   ├── OrderEventHandler.java
 │
 * Controller for managing Order in the Example system.
 * Provides endpoints for creating, updating, listing, and deleting Order entities.
 *
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Slf4j
@RestController
@RequestMapping("/api/example/orders")
@Tag(name = "Example Module Orders Management第一版", description = "提供 ExampleOrder 的管理功能，包括新增、查詢、更新和刪除。")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
public class DemoRestController {

    private final DemoCreateUseCase orderCreateUseCase;
    private final DemoFindUseCase orderFindUseCase;
    private final DemoUpdateUseCase orderUpdateUseCase;
    private final DemoCancelUseCase orderCancelUseCase;

    /**
     * 📌 創建訂單
     */
    @PostMapping
    public ResponseEntity<DemoResponseDTO> createOrder(@RequestBody DemoRequestDTO request) {
        log.info("📌 創建訂單，客戶 ID：{}", request.getCustomerId());
        DemoResponseDTO response = orderCreateUseCase.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * 📌 取得單筆訂單
     */
    @GetMapping("/{id}")
    public ResponseEntity<DemoResponseDTO> getOrder(@PathVariable Long id) {
    	log.info("📌 查詢訂單，ID: {}", id);
        DemoResponseDTO response = orderFindUseCase.findById(id);
        if (response != null) {
            return ResponseEntity.ok(response); // ✅ 找到資料，回傳 200 OK
        }
        return ResponseEntity.notFound().build(); // ❌ 找不到，回傳 404
    }

    /**
     * 📌 取得客戶的所有訂單（支援分頁）
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<DemoResponseDTO>> getOrdersByCustomer(@PathVariable String customerId, Pageable pageable) {
        log.info("📌 查詢客戶所有訂單，客戶 ID: {}", customerId);
        return ResponseEntity.ok(orderFindUseCase.findByCustomerId(customerId, pageable));
    }

    /**
     * 📌 取得所有訂單（支援分頁）
     */
    @GetMapping
    public ResponseEntity<Page<DemoResponseDTO>> getAllOrders(Pageable pageable) {
        log.info("📌 查詢所有訂單");
        return ResponseEntity.ok(orderFindUseCase.findAllOrders(pageable));
    }
    
    /**
     * 📌 更新訂單
     */
    @PutMapping("/{id}")
    public ResponseEntity<DemoResponseDTO> updateOrder(@PathVariable Long id, @RequestBody DemoRequestDTO request) {
        log.info("📌 更新訂單，ID: {}", id);
        DemoResponseDTO response = orderUpdateUseCase.updateOrder(id, request);
        return ResponseEntity.ok(response); // ✅ 正確寫法（不使用 map）
    }

    /**
     * 📌 取消訂單
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        log.info("📌 取消訂單，ID: {}", id);
        boolean success = orderCancelUseCase.cancelOrder(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
}
