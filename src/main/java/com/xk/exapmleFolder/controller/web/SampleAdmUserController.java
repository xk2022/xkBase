package com.xk.exapmleFolder.controller.web;

import com.xk.exapmleFolder.application.mapper.UserDtoMapper;
import com.xk.exapmleFolder.application.model.*;
import com.xk.exapmleFolder.application.usecase.UserCreateUseCase;
import com.xk.exapmleFolder.application.usecase.UserDeleteUseCase;
import com.xk.exapmleFolder.application.usecase.UserFindUseCase;
import com.xk.exapmleFolder.application.usecase.UserUpdateUseCase;
import com.xk.exapmleFolder.controller.api.SampleAdmUserApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * 📌 `SampleAdmUserController` - 負責管理 **使用者 API**
 * <p>
 * - 實作 {@link SampleAdmUserApi}
 * - 呼叫對應的 UseCase，回傳統一 BaseResult
 *
 * @author yuan Created on 2025/07/31.
 * @author yuan Updated on 2025/07/31. something note here.
 * Sample from fubonlife project
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class SampleAdmUserController implements SampleAdmUserApi {

    final UserDtoMapper mapper;
    /** 使用者建立流程 UseCase */
    final UserCreateUseCase userCreateUseCase;
    /** 使用者更新流程 UseCase */
    final UserUpdateUseCase userUpdateUseCase;
    /** 使用者刪除流程 UseCase */
    final UserDeleteUseCase userDeleteUseCase;
    /** 使用者查詢流程 UseCase */
    final UserFindUseCase userFindUseCase;

    /**
     * 📌 [POST] 新增使用者
     *
     * - 接收 {@link UserCreateRequest}
     * - 呼叫 {@link UserCreateUseCase} 執行新增
     */
    @Override
    public void createUser(UserCreateRequest request) {
        userCreateUseCase.execute(mapper.toSaveCmd(request));
    }

    /**
     * 📌 [POST] 編輯使用者
     *
     * - 接收 {@link UserReplaceRequest}
     * - 呼叫 {@link UserUpdateUseCase} 執行更新
     */
    @Override
    public void updateUser(UserReplaceRequest request) {
        userUpdateUseCase.execute(request);
    }

    /**
     * 📌 [POST] 刪除使用者
     *
     * - 接收 {@link UserDeleteRequest}
     * - 呼叫 {@link UserDeleteUseCase} 執行刪除
     */
    @Override
    public void deleteUser(UserDeleteRequest request) {
        userDeleteUseCase.execute(request);
    }

    /**
     * 📌 [POST] 查詢使用者
     *
     * - 接收 {@link UserListQueryRequest} 作為查詢條件
     * - 呼叫 {@link UserFindUseCase} 執行查詢
     * - 回傳 {@link UserListQueryDto} 查詢結果
     *
     * @param request 查詢條件物件
     * @return 使用者清單查詢結果 DTO
     */
    @Override
    public UserListResponse queryUsers(UserListQueryRequest request) {
        return mapper.toResponse(userFindUseCase.execute(request));
    }

}
