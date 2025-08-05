package com.xk.exapmleFolder.application.usecase.impl;

import com.xk.exapmleFolder.application.model.UserDeleteRequest;
import com.xk.exapmleFolder.application.usecase.UserDeleteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDeleteUseCaseIpml implements UserDeleteUseCase {
    @Override
    public void execute(UserDeleteRequest request) {

    }
}
