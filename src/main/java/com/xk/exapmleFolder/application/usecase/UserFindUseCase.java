package com.xk.exapmleFolder.application.usecase;

import com.xk.exapmleFolder.application.model.UserListQueryDto;
import com.xk.exapmleFolder.application.model.UserListQueryRequest;
import com.xk.exapmleFolder.application.model.UserListResponse;

public interface UserFindUseCase {

    UserListQueryDto execute(UserListQueryRequest request);

}
