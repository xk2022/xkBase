package com.xk.adm.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xk.adm.application.dto.DictionaryDTO;
import com.xk.common.base.BaseResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 📌 `AdmDictionaryController` - 負責管理 **字典數據 API**
 * 
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 * 
 * @author yuan Created on 2025/02/25.
 */
@Tag(name = "Dictionary Management", description = "字典數據管理")
@RestController
@RequestMapping("/api/adm/dictionary")
@RequiredArgsConstructor
@Slf4j
public class AdmDictionaryController {

//    private final AdmDictionaryUseCase admDictionaryUseCase;

    @Operation(summary = "獲取所有字典數據", description = "查詢所有字典數據")
    @GetMapping("/all")
    public BaseResult<List<DictionaryDTO>> getAllDictionaries() {
//        return BaseResult.success(admDictionaryUseCase.getAllDictionaries(), "成功");
        return null;
    }

    @Operation(summary = "根據類型獲取字典數據", description = "查詢特定類型的字典數據")
    @GetMapping("/{type}")
    public BaseResult<List<DictionaryDTO>> getDictionariesByType(@PathVariable String type) {
//        return BaseResult.success(admDictionaryUseCase.getDictionariesByType(type), "成功");
        return null;
    }

    @Operation(summary = "新增字典數據", description = "新增一條字典數據")
    @PostMapping("/create")
    public BaseResult<DictionaryDTO> createDictionary(@RequestBody DictionaryDTO dictionaryDTO) {
//        return BaseResult.success(admDictionaryUseCase.createDictionary(dictionaryDTO), "成功");
        return null;
    }

    @Operation(summary = "更新字典數據", description = "更新現有的字典數據")
    @PutMapping("/update")
    public BaseResult<DictionaryDTO> updateDictionary(@RequestBody DictionaryDTO dictionaryDTO) {
//        return BaseResult.success(admDictionaryUseCase.updateDictionary(dictionaryDTO), "成功");
        return null;
    }

    @Operation(summary = "刪除字典數據", description = "根據 ID 刪除字典數據")
    @DeleteMapping("/delete/{id}")
    public BaseResult<Boolean> deleteDictionary(@PathVariable String id) {
//        return BaseResult.success(admDictionaryUseCase.deleteDictionary(id), "成功");
        return null;
    }
    
}