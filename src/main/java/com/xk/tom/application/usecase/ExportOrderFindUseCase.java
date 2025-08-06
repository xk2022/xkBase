package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ExportOrderQueryDto;
import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.domain.model.enums.OrderStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderFindUseCase
 * - 查詢出口訂單用例
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ExportOrderFindUseCase {

    OrderResponseDto getByUuid(UUID uuid);

    Page<OrderResponseDto> findAll(Pageable pageable);

    List<OrderResponseDto> query(@Valid ExportOrderQueryDto query);

}
