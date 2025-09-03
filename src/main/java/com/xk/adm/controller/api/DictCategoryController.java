package com.xk.adm.controller.api;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.application.usecase.DictCategoryCreateUseCase;
import com.xk.common.base.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 📌 `DIctCategoryController` - 負責管理 選單類別 API**
 *
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author Hank Created on 2025/09/03.
 */
@Tag(name = "DIctCategory Management", description = "選單類別管理")
@RestController
@RequestMapping("/api/adm/dictcategory")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DictCategoryController {

    private final DictCategoryCreateUseCase dictCategoryCreateUseCase;


    @Operation(summary = "新增選單類別" ,description = "新增選單類別")
    @PostMapping("/save")
    public BaseResult<DictCategoryResponse> create(@RequestBody @Valid DictCategoryRequest request ){
        DictCategoryResponse response = dictCategoryCreateUseCase.create(request);
        return BaseResult.success(response ,"新增選單類別成功");
    }
}
