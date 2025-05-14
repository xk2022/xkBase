package com.xk.tom.application.usecase.Impl;

import com.xk.tom.application.usecase.OrderDeleteUseCase;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.repository.ImportOrderRepository;
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

    @Override
    public Boolean deletedImportOrder(Long importId) {
        //查詢是否有該筆訂單，且狀態為PENDING
        Optional<ImportOrderAggreate> orderOptional = importOrderService.findByImportIdAndStatusPending(importId);
        if (orderOptional.isEmpty()) {
            log.warn("訂單不存在或狀態不為PENDING，importId: {}", importId);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "訂單不存在或狀態不為PENDING");
        }
        
        try {
            int result = importOrderService.deleteImportOrder(importId);
            if (result <= 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "刪除訂單失敗");
            }
            return true;
        } catch (Exception e) {
            log.error("刪除訂單時發生錯誤，importId: {}", importId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "刪除訂單時發生錯誤");
        }
    }
}
