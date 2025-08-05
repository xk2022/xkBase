package com.xk.exapmleFolder.application.usecase.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.application.mapper.UserDtoMapper;
import com.xk.exapmleFolder.application.model.SaveUserCmd;
import com.xk.exapmleFolder.application.model.UserCreateRequest;
import com.xk.exapmleFolder.application.usecase.UserCreateUseCase;
import com.xk.exapmleFolder.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreateUseCaseImpl implements UserCreateUseCase {

    final UserService userService;
    final UserDtoMapper mapper;


    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void execute(SaveUserCmd cmd) {
        userService.createUser(mapper.toCreateEntity(cmd));
    }

}