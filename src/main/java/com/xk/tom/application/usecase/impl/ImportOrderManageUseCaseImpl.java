package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.model.ImportOrderRequestDto;
import com.xk.tom.application.model.ImportOrderResponseDto;
import com.xk.tom.application.usecase.ImportOrderManageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderManageUseCaseImpl implements ImportOrderManageUseCase {
    @Override
    public ImportOrderResponseDto create(ImportOrderRequestDto request) {
        return null;
    }

    @Override
    public ImportOrderResponseDto update(UUID uuid, ImportOrderRequestDto request) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
