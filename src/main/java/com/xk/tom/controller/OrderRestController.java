package com.xk.tom.controller;


import com.xk.common.base.BaseResult;

import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.application.model.*;
import com.xk.tom.application.usecase.*;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.aggreate.OrderTypeEnum;
import com.xk.tom.domain.service.ImportOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tom/order")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "Order Management", description = "提供 Order 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class OrderRestController {

    private final OrderCreateUseCase orderCreateUseCase;
    private final ImportOrderFindUseCase importOrderFindUserCase;
    private final ExportOrderFindUseCase exportOrderFindUseCase;
    private final OrderDeleteUseCase orderDeleteUseCase;
    private final ImportOrderUpdateUseCase importOrderUpdateUseCase;
    private final ExportOrderUpdateUseCase exportOrderUpdateUseCase;


    @Operation(summary = "新增訂單", description = "創建一個新的訂單。")
    @PostMapping("/createOrder")
    public BaseResult<OrderResponseDTO> createUser(
            @RequestBody @Validated @NotNull OrderCreateDTO request) throws ParseException {
        OrderResponseDTO orderResponseDTO = orderCreateUseCase.create(request);
        return BaseResult.success(orderResponseDTO, "新增訂單成功");
    }

    @Operation(summary = "查詢進口訂單", description = "查詢進口訂單")
    @PostMapping("getImportOrder")
    public BaseResult<ImportOrderResponseDTO> getImportOrder(@RequestBody @Valid ImportOrderDTO request) {

        ImportOrderResponseDTO orderResponseDTOs = importOrderFindUserCase.getImportOrder(request);
        return BaseResult.success(orderResponseDTOs, "查詢訂單完成");
    }

    @Operation(summary = "查詢出口訂單", description = "查詢出口訂單")
    @PostMapping("getExportOrder")
    public BaseResult<ExportOrderResponseDTO> getExportOrder(@RequestBody @Valid ExportOrderDTO request) {

        ExportOrderResponseDTO orderResponseDTOs = exportOrderFindUseCase.getExportOrder(request);
        return BaseResult.success(orderResponseDTOs, "查詢訂單完成");
    }

    @Operation(summary = "刪除進口訂單", description = "刪除進口訂單")
    @DeleteMapping("/{importId}")
    public BaseResult<Boolean> deleteImportOrder(@PathVariable Long importId) {
        orderDeleteUseCase.deletedImportOrder(importId);
        return BaseResult.success(true, "刪除成功");
    }

    @Operation(summary = "刪除出口訂單", description = "刪除出口訂單")
    @DeleteMapping("/{exportId}")
    public BaseResult<Boolean> deleteExportOrder(@PathVariable Long exportId) {
        orderDeleteUseCase.deletedExportOrder(exportId);
        return BaseResult.success(true, "刪除成功");
    }


    @Operation(summary = "訂單類型查詢", description = "訂單類型查詢")
    @PostMapping("/getOrderByStatus")
    public BaseResult<List<OrderResponseDTO>> getOrderByStatus(@RequestBody @Validated @NotNull String status) {
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(status);
        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
        if (orderTypeEnum.name().equals("IMPORT")) {
            responseDTOs = importOrderFindUserCase.getOrderByOrderTypeImport();
            return BaseResult.success(responseDTOs, "查詢進口訂單完成");
        } else {
            responseDTOs = exportOrderFindUseCase.getOrderByOrderTypeExport();
            return BaseResult.success(responseDTOs, "查詢出口訂單完成");
        }

    }

    @Operation(summary = "客戶名稱查詢訂單", description = "客戶名稱查詢訂單")
    @PostMapping("getOrderByCustomerName")
    public BaseResult<List<OrderResponseDTO>> getOrderByCustomerName(@RequestBody @Validated @NotNull OrderRequestDTO requestDTO) {
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(requestDTO.status());
        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
        if (orderTypeEnum.name().equals("IMPORT")) {
            responseDTOs = importOrderFindUserCase.getOrderByCustomerNameAndOrderTypeImport(requestDTO.customerName());
            return BaseResult.success(responseDTOs, "查詢進口訂單完成");
        } else {
            responseDTOs = exportOrderFindUseCase.getOrderByCustomerNameAndOrderTypeExport(requestDTO.customerName());
            return BaseResult.success(responseDTOs, "查詢出口訂單完成");
        }
    }

    @Operation(summary = "客戶模糊查詢進口訂單", description = "客戶模糊查詢進口訂單")
    @PostMapping("/getImportOrderByKeyWord")
    public BaseResult<List<OrderResponseDTO>> getImportOrderByKeyWord(@RequestBody String keyWord) {

        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
        responseDTOs = importOrderFindUserCase.getImportOrderByKeyWord(keyWord);

        return BaseResult.success(responseDTOs, "查詢進口訂單完成");
    }


    @Operation(summary = "客戶模糊查詢出口訂單", description = "客戶模糊查詢出口訂單")
    @PostMapping("/getExportOrderByKeyWord")
    public BaseResult<List<OrderResponseDTO>> getExportOrderByKeyWord(@RequestBody String keyWord) {

        List<OrderResponseDTO> responseDTOs = new ArrayList<>();
        responseDTOs = exportOrderFindUseCase.getExportOrderByKeyWord(keyWord);

        return BaseResult.success(responseDTOs, "查詢出口訂單完成");
    }


    @Operation(summary = "更新進口訂單", description = "更新進口訂單")
    @PostMapping("/updateImportOrder/{importId}")
    public BaseResult<ImportOrderResponseDTO> updateImportOrder(@PathVariable Long importId, @RequestBody ImportOrderRequestDTO request) throws ParseException {
        ImportOrderResponseDTO newImportOrderResponseDTO = importOrderUpdateUseCase.updateImportOrder(importId, request);
        if (newImportOrderResponseDTO != null) {
            return BaseResult.success(newImportOrderResponseDTO, "更新成功");
        } else {
            return BaseResult.failure(HttpStatus.NOT_FOUND, "更新失敗", false);
        }
    }

    @Operation(summary = "更新出口訂單", description = "更新出口訂單")
    @PutMapping("/updateExportOrder/{exportId}")
    public BaseResult<ExportOrderResponseDTO> updateExportOrder(@PathVariable Long exportId, @RequestBody ExportOrderRequestDTO request) throws ParseException {
        ExportOrderResponseDTO newExportOrderResponseDTO = exportOrderUpdateUseCase.updateExportOrder(exportId, request);
        if (newExportOrderResponseDTO != null) {
            return BaseResult.success(newExportOrderResponseDTO, "更新成功");
        } else {
            return BaseResult.failure(HttpStatus.NOT_FOUND, "更新失敗", false);
        }
    }


}
