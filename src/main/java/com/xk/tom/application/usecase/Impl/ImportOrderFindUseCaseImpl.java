package com.xk.tom.application.usecase.Impl;

import com.xk.tom.application.model.ImportOrderDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderFindUseCaseImpl implements ImportOrderFindUseCase {
    @Override
    public List<ImportOrderResponseDTO> getImportOrder(ImportOrderDTO request) {
        return List.of();
    }
}
