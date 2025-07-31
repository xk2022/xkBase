package com.xk.tom.application.usecase.Impl;

import com.xk.exapmleFolder.exception.DemoNotFoundException;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.application.usecase.OrderDeleteUseCase;
import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.service.ExportOrderService;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDeletedUseCaseImpl implements OrderDeleteUseCase {

    private final ImportOrderService importOrderService;
    private final ExportOrderService exportOrderService;

    @Override
    public void deletedImportOrder(Long importId) {
        //查詢是否有該筆訂單，且狀態為PENDING
        Optional<ImportOrderAggreate> orderOptional = importOrderService.findByImportIdAndStatusPending(importId);
        if (orderOptional.isEmpty()) {
            log.warn("訂單不存在或狀態不為PENDING，importId: {}", importId);
            throw new DemoNotFoundException("訂單不存在或狀態不為PENDING，importId: {}", importId);
        }

        try {
            int result = importOrderService.deleteImportOrder(importId);
            if (result <= 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "刪除訂單失敗");
            }
        } catch (Exception e) {
            log.error("刪除訂單時發生錯誤，importId: {}", importId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "刪除訂單時發生錯誤");
        }
    }

    @Override
    public void deletedExportOrder(Long exportId) {

        Optional<ExportOrderAggreate> orderOptional = exportOrderService.findByImportIdAndStatusPending(exportId);
        if (orderOptional.isEmpty()) {
            log.warn("訂單不存在或狀態不為PENDING，importId: {}", exportId);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "訂單不存在或狀態不為PENDING");
        }

        try {
            int result = importOrderService.deleteImportOrder(exportId);
            if (result <= 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "刪除訂單失敗");
            }
        } catch (Exception e) {
            log.error("刪除訂單時發生錯誤，importId: {}", exportId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "刪除訂單時發生錯誤");
        }
    }


}
