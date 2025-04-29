package com.xk.tom.controller;


import com.xk.common.base.BaseResult;

import com.xk.tom.application.model.OrderCreateDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderCreateUseCase;
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

@RestController
@RequestMapping("/api/tom/order")
@RequiredArgsConstructor // ✅ 使用建構子注入，減少 @Autowired
@Validated
@Tag(name = "Order Management", description = "提供 Order 的管理功能，包括新增、查詢、更新和刪除。")
@Slf4j
public class OrderRestController {

    private final ImportOrderCreateUseCase importOrderCreateUseCase;


    @Operation(summary = "新增訂單", description = "創建一個新的 ImportOrder。")
    @PostMapping
    public BaseResult<OrderResponseDTO> createUser(
            @RequestBody @Validated @NotNull OrderCreateDTO request) {
        OrderResponseDTO orderResponseDTO = importOrderCreateUseCase.create(request);
        return BaseResult.success(orderResponseDTO, "新增訂單成功");
    }
}
