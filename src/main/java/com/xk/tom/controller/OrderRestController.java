package com.xk.tom.controller;


import com.xk.common.base.BaseResult;

import com.xk.tom.application.model.*;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.application.usecase.ImportOrderCreateUseCase;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tom/order")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "Order Management", description = "提供 Order 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class OrderRestController {

    private final ImportOrderCreateUseCase importOrderCreateUseCase;
    private final ImportOrderFindUseCase importOrderFindUserCase;
    private final ExportOrderFindUseCase exportOrderFindUseCase;


    @Operation(summary = "新增訂單", description = "創建一個新的訂單。")
    @PostMapping("/createOrder")
    public BaseResult<OrderResponseDTO> createUser(
            @RequestBody @Validated @NotNull OrderCreateDTO request) {
        OrderResponseDTO orderResponseDTO = importOrderCreateUseCase.create(request);
        return BaseResult.success(orderResponseDTO, "新增訂單成功");
    }

    @Operation(summary = "查詢進口訂單", description = "查詢進口訂單")
    @PostMapping("getImportOrder")
    public BaseResult<List<ImportOrderResponseDTO>> getImportOrder(@RequestBody @Validated @NotNull ImportOrderDTO request) {
        List<ImportOrderResponseDTO> orderResponseDTOs = importOrderFindUserCase.getImportOrder(request);
        return BaseResult.success(orderResponseDTOs, "查詢訂單完成");
    }

    @Operation(summary = "查詢出口訂單", description = "查詢出口訂單")
    @PostMapping("getExportOrder")
    public BaseResult<List<ExportOrderResponseDTO>> getExportOrder(@RequestBody @Validated @NotNull ExportOrderDTO request) {
        List<ExportOrderResponseDTO> orderResponseDTOs = exportOrderFindUseCase.getExportOrder(request);
        return BaseResult.success(orderResponseDTOs, "查詢訂單完成");
    }
}
