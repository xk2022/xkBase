package com.xk.adm.application.usecase.impl;

import com.xk.adm.application.usecase.DictItemDeleteUseCase;
import com.xk.adm.domain.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 `DictItemDeleteUseCaseImpl` - 负责選單項目的刪除逻辑
 *
 * - 处理 `DictItemRequest` 并转换为 `DictItemPo`
 * - 通过 `DictItemService` 进行业务验证和存储
 * - 返回 `DictItemResponse`
 *
 * @author hank Created on 2025/09/09.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DictItemDeleteUseCaseImpl implements DictItemDeleteUseCase {

    private final DictItemService service;

    @Override
    public void delete(UUID uuid) throws NotFoundException {
        log.info("[UseCase] 刪除選單項目 uuid={}",uuid);
        service.delete(uuid);
    }
}
