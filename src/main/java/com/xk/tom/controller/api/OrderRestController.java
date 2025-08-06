package com.xk.tom.controller.api;

import com.xk.common.base.BaseResult;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 📌 OrderRestController（共用入口 / 聚合 API）
 * 用途：提供共用或跨 import/export 的 API
 * <p>
 * 路由範例：
 * GET /api/orders → 查詢所有訂單（import + export 都一起）
 * GET /api/orders/{uuid} → 查詢單筆訂單（不管是 import/export）
 * GET /api/orders/reports/daily → 匯出統計報表（混合）
 * <p>
 * 呼叫的 UseCase：
 * 一般會呼叫 OrderFindUseCase、OrderReportUseCase
 *
 * 👉 這一隻是「總控入口」，適合提供 跨類型的統一 API。
 *
 * @author yuan Created on 2025/08/05.
 */
@RestController
@RequestMapping("/api/tom/orders")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "Order Management", description = "共用訂單 API，包括查詢、報表與跨 import/export 功能")
@Slf4j
public class OrderRestController {

    private final ImportOrderFindUseCase importOrderFindUseCase;
    private final ExportOrderFindUseCase exportOrderFindUseCase;

    /**
     * 跨 import/export 查詢
     */
    @GetMapping
    @Operation(summary = "查詢所有訂單", description = "查詢所有進口與出口訂單")
    public BaseResult<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> importOrders = importOrderFindUseCase.query(null);
        List<OrderResponseDto> exportOrders = exportOrderFindUseCase.query(null);
        List<OrderResponseDto> allOrders = new ArrayList<>();
        allOrders.addAll(importOrders);
        allOrders.addAll(exportOrders);
        return BaseResult.success(allOrders, "查詢所有訂單完成");
    }

    /**
     * 報表
     */
//    @GetMapping("/reports/daily")
//    @Operation(summary = "每日報表", description = "匯出每日訂單報表")
//    public BaseResult<ReportDto> getDailyReport() {
//        ReportDto report = orderReportUseCase.generateDailyReport();
//        return BaseResult.success(report, "每日報表完成");
//    }











//    @Operation(summary = "新增訂單", description = "創建一個新的訂單。")
//    @PostMapping("/createOrder")
//    public BaseResult<OrderResponseDto> createUser(@RequestBody @Validated @NotNull OrderCreateDTO request) throws ParseException {
//        OrderResponseDTO orderResponseDTO = orderCreateUseCase.create(request);
//        return BaseResult.success(orderResponseDTO, "新增訂單成功");
//    }
//
//    @Operation(summary = "查詢進口訂單", description = "查詢進口訂單")
//    @PostMapping("getImportOrder")
//    public BaseResult<ImportOrderResponseDto> getImportOrder(@RequestBody @Valid ImportOrderDTO request) {
//
//        ImportOrderResponseDto orderResponseDTOs = importOrderFindUserCase.getImportOrder(request);
//        return BaseResult.success(orderResponseDTOs, "查詢訂單完成");
//    }
//
//    @Operation(summary = "查詢出口訂單", description = "查詢出口訂單")
//    @PostMapping("getExportOrder")
//    public BaseResult<ExportOrderResponseDto> getExportOrder(@RequestBody @Valid ExportOrderDTO request) {
//
//        ExportOrderResponseDto orderResponseDTOs = exportOrderFindUseCase.getExportOrder(request);
//        return BaseResult.success(orderResponseDTOs, "查詢訂單完成");
//    }
//
//    @Operation(summary = "刪除進口訂單", description = "刪除進口訂單")
//    @DeleteMapping("/{importId}")
//    public BaseResult<Boolean> deleteImportOrder(@PathVariable Long importId) {
//        orderDeleteUseCase.deletedImportOrder(importId);
//        return BaseResult.success(true, "刪除成功");
//    }
//
//    @Operation(summary = "刪除出口訂單", description = "刪除出口訂單")
//    @DeleteMapping("/{exportId}")
//    public BaseResult<Boolean> deleteExportOrder(@PathVariable Long exportId) {
//        orderDeleteUseCase.deletedExportOrder(exportId);
//        return BaseResult.success(true, "刪除成功");
//    }
//
//
//    @Operation(summary = "訂單類型查詢", description = "訂單類型查詢")
//    @PostMapping("/getOrderByStatus")
//    public BaseResult<List<OrderResponseDTO>> getOrderByStatus(@RequestBody @Validated @NotNull String status) {
//        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(status);
//        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
//        if (orderTypeEnum.name().equals("IMPORT")) {
//            responseDTOs = importOrderFindUserCase.getOrderByOrderTypeImport();
//            return BaseResult.success(responseDTOs, "查詢進口訂單完成");
//        } else {
//            responseDTOs = exportOrderFindUseCase.getOrderByOrderTypeExport();
//            return BaseResult.success(responseDTOs, "查詢出口訂單完成");
//        }
//
//    }
//
//    @Operation(summary = "客戶名稱查詢訂單", description = "客戶名稱查詢訂單")
//    @PostMapping("getOrderByCustomerName")
//    public BaseResult<List<OrderResponseDTO>> getOrderByCustomerName(@RequestBody @Validated @NotNull OrderRequestDTO requestDTO) {
//        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(requestDTO.status());
//        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
//        if (orderTypeEnum.name().equals("IMPORT")) {
//            responseDTOs = importOrderFindUserCase.getOrderByCustomerNameAndOrderTypeImport(requestDTO.customerName());
//            return BaseResult.success(responseDTOs, "查詢進口訂單完成");
//        } else {
//            responseDTOs = exportOrderFindUseCase.getOrderByCustomerNameAndOrderTypeExport(requestDTO.customerName());
//            return BaseResult.success(responseDTOs, "查詢出口訂單完成");
//        }
//    }
//
//    @Operation(summary = "客戶模糊查詢進口訂單", description = "客戶模糊查詢進口訂單")
//    @PostMapping("/getImportOrderByKeyWord")
//    public BaseResult<List<OrderResponseDTO>> getImportOrderByKeyWord(@RequestBody String keyWord) {
//
//        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
//        responseDTOs = importOrderFindUserCase.getImportOrderByKeyWord(keyWord);
//
//        return BaseResult.success(responseDTOs, "查詢進口訂單完成");
//    }
//
//
//    @Operation(summary = "客戶模糊查詢出口訂單", description = "客戶模糊查詢出口訂單")
//    @PostMapping("/getExportOrderByKeyWord")
//    public BaseResult<List<OrderResponseDTO>> getExportOrderByKeyWord(@RequestBody String keyWord) {
//
//        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
//        responseDTOs = exportOrderFindUseCase.getExportOrderByKeyWord(keyWord);
//
//        return BaseResult.success(responseDTOs, "查詢出口訂單完成");
//    }
//
//
//    @Operation(summary = "更新進口訂單", description = "更新進口訂單")
//    @PostMapping("/updateImportOrder/{importId}")
//    public BaseResult<ImportOrderResponseDto> updateImportOrder(@PathVariable Long importId, @RequestBody ImportOrderRequestDTO request) throws ParseException {
//        ImportOrderResponseDto newImportOrderResponseDTO = importOrderUpdateUseCase.updateImportOrder(importId, request);
//        if (newImportOrderResponseDTO != null) {
//            return BaseResult.success(newImportOrderResponseDTO, "更新成功");
//        } else {
//            return BaseResult.failure(HttpStatus.NOT_FOUND, "更新失敗", false);
//        }
//    }
//
//    @Operation(summary = "更新出口訂單", description = "更新出口訂單")
//    @PutMapping("/updateExportOrder/{exportId}")
//    public BaseResult<ExportOrderResponseDto> updateExportOrder(@PathVariable Long exportId, @RequestBody ExportOrderRequestDto request) throws ParseException {
//        ExportOrderResponseDto newExportOrderResponseDTO = exportOrderUpdateUseCase.updateExportOrder(exportId, request);
//        if (newExportOrderResponseDTO != null) {
//            return BaseResult.success(newExportOrderResponseDTO, "更新成功");
//        } else {
//            return BaseResult.failure(HttpStatus.NOT_FOUND, "更新失敗", false);
//        }
//    }


}
