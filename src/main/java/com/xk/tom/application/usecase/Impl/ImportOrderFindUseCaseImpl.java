package com.xk.tom.application.usecase.Impl;

import com.xk.tom.application.model.ImportOrderDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderFindUseCaseImpl implements ImportOrderFindUseCase {

    private final ImportOrderService importOrderService;
    @Override
    public ImportOrderResponseDTO getImportOrder(ImportOrderDTO request) {
        ImportOrderResponseDTO responseDTO = new ImportOrderResponseDTO();

        responseDTO = importOrderService.getImportOrder(request.orderid());

        return responseDTO;
    }
}
