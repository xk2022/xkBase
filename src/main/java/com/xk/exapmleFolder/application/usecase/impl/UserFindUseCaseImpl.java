package com.xk.exapmleFolder.application.usecase.impl;

import com.xk.exapmleFolder.application.model.UserListQueryDto;
import com.xk.exapmleFolder.application.model.UserListQueryRequest;
import com.xk.exapmleFolder.application.usecase.UserFindUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserFindUseCaseImpl implements UserFindUseCase {
    @Override
    public UserListQueryDto execute(UserListQueryRequest request) {
        return null;
    }
}
