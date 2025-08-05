package com.xk.exapmleFolder.domain.service.impl;

import com.xk.exapmleFolder.domain.dao.repository.UserRepository;
import com.xk.exapmleFolder.domain.model.UserBO;
import com.xk.exapmleFolder.domain.model.UserEntity;
import com.xk.exapmleFolder.domain.model.example.EmailVO;
import com.xk.exapmleFolder.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 📌 `UserService` - 專責使用者領域邏輯
 *
 * - 適合放置跨多個實體的商業規則
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserBO save(UserBO user) {
        return null;
    }

    @Override
    public void createUser(UserEntity createEntity) {

    }
}