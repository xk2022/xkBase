package com.xk.adm.controller.api;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;

import com.xk.adm.application.dto.DictItemRequest;
import com.xk.adm.application.dto.DictItemResponse;
import com.xk.adm.application.usecase.DictItemDeleteUseCase;
import com.xk.adm.application.usecase.DictItemCreateUseCase;
import com.xk.common.base.BaseResult;
import com.xk.common.util.dto.JwtUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 📌 `DictItemController` - 負責管理 選單項目 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/09/10.
 */
@Tag(name = "DIctCategory Management", description = "選單項目管理")
@RestController
@RequestMapping("/api/adm/dictitem")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DictItemController {

    private final DictItemCreateUseCase createUseCase;
    private final DictItemDeleteUseCase deleteUseCase;


    @Operation(summary = "新增選單項目" ,description = "新增選單項目")
    @PostMapping("/save")
    public BaseResult<DictItemResponse> create(@RequestBody @Valid DictItemRequest request , @AuthenticationPrincipal JwtUserDTO userDTO) throws NotFoundException {
        DictItemResponse response = createUseCase.create(request);
        return BaseResult.success(response ,"新增選單類別成功");
    }


    @Operation(summary = "刪除選單類別" ,description = "刪除選單類別")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void>  delete(
            @Parameter(description = "需要刪除的選單類別 ID", required = true)  @PathVariable UUID uuid) throws NotFoundException {
        log.info(" [API] 刪除選單類別 - UUID: {}", uuid);

        deleteUseCase.delete(uuid); // 傳遞 UUID 進入 UseCase

        return ResponseEntity.noContent().build();

    }
}
