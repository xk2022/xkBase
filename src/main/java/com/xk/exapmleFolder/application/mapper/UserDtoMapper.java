package com.xk.exapmleFolder.application.mapper;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.model.SaveUserCmd;
import com.xk.exapmleFolder.application.model.UserCreateRequest;
import com.xk.exapmleFolder.application.model.UserListQueryDto;
import com.xk.exapmleFolder.application.model.UserListResponse;
import com.xk.exapmleFolder.domain.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public SaveUserCmd toSaveCmd(UserCreateRequest request) {
        // ✅ 轉換 request -> CMD
        return XkBeanUtils.copyProperties(request, SaveUserCmd::new);
    }

    public UserEntity toCreateEntity(SaveUserCmd cmd) {
        // ✅ SaveUserCmd -> UserEntity
        return XkBeanUtils.copyProperties(cmd, UserEntity::new);
    }

    public UserListResponse toResponse(UserListQueryDto dto) {
        return XkBeanUtils.copyProperties(dto, UserListResponse::new);
    }

}
