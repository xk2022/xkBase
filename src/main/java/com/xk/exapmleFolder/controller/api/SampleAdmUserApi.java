package com.xk.exapmleFolder.controller.api;

import com.xk.exapmleFolder.application.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 📌 `SampleApi` - 負責管理 **使用者 API**
 * <p>
 * - 提供 `CRUD` 操作
 * - 支援分頁查詢
 * - `DTO` 物件與 `UseCase` 互動
 *
 * @author yuan Created on 2025/07/31.
 * @author yuan Updated on 2025/07/31. something note here.
 * Sample from fubonlife project
 */
@Tag(name = "Example Module Users Management第三版(後台管理_使用者權限選單)", description = "使用者相關操作 API")
public interface SampleAdmUserApi {

    @Operation(summary = "新增_使用者")
    @PostMapping("/api/sample/admUser/create")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success operation")})
    void createUser(@RequestBody @Valid UserCreateRequest request);

    @Operation(summary = "編輯_使用者")
    @PostMapping("/api/sample/admUser/modify")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success operation")})
    void updateUser(@RequestBody @Valid UserReplaceRequest request);

    @Operation(summary = "刪除_使用者")
    @PostMapping("/api/sample/admUser/remove")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success operation")})
    void deleteUser(@RequestBody @Valid UserDeleteRequest request);

    @Operation(summary = "查詢_使用者", description = "依據條件查詢使用者資料")
    @PostMapping("/api/sample/admUser/query")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success operation")})
    UserListResponse queryUsers(@RequestBody @Valid UserListQueryRequest request);

}
