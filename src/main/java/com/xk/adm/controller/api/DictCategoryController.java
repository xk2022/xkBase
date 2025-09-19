package com.xk.adm.controller.api;

import com.xk.adm.application.dto.DictCategoryRequest;
import com.xk.adm.application.dto.DictCategoryResponse;
import com.xk.adm.application.usecase.DictCategoryCreateUseCase;
import com.xk.adm.application.usecase.DictCategoryDeleteUseCase;
import com.xk.adm.application.usecase.DictCategoryFindUseCase;
import com.xk.common.base.BaseResult;
import com.xk.common.util.dto.JwtUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    private final DictCategoryDeleteUseCase deleteUseCase;
    private final DictCategoryFindUseCase findUseCase;


    @Operation(summary = "新增選單類別" ,description = "新增選單類別")
    @PostMapping("/save")
    public BaseResult<DictCategoryResponse> create(@RequestBody @Valid DictCategoryRequest request , @AuthenticationPrincipal JwtUserDTO userDTO){
        DictCategoryResponse response = dictCategoryCreateUseCase.create(request);
        return BaseResult.success(response ,"新增選單類別成功");
    }


    @Operation(summary = "刪除選單類別" ,description = "刪除選單類別")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void>  delete(
            @Parameter(description = "需要刪除的選單類別 ID", required = true)  @PathVariable UUID uuid){
        log.info(" [API] 刪除選單類別 - UUID: {}", uuid);

        deleteUseCase.delete(uuid); // 傳遞 UUID 進入 UseCase

        return ResponseEntity.noContent().build();

    }

    @GetMapping("getDictCategory")
    public BaseResult<DictCategoryResponse> getDictCategory(@RequestParam( name ="categoryCode" ,required = true) String categoryCode
            , @AuthenticationPrincipal JwtUserDTO userDTO ){
        DictCategoryResponse response = findUseCase.getDictCategory(categoryCode);
        return BaseResult.success(response ,"查詢選單類別成功");
    }


}
