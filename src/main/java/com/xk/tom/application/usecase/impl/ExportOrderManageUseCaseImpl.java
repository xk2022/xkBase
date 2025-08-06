package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.model.ExportOrderRequestDto;
import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderManageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 *
 * @author yuan Created on 2025/08/05.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExportOrderManageUseCaseImpl implements ExportOrderManageUseCase {
    @Override
    public ExportOrderResponseDto create(ExportOrderRequestDto request) {
        return null;
    }

    @Override
    public ExportOrderResponseDto update(UUID uuid, ExportOrderRequestDto request) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
