package com.xk.upms.usecase.user.impl;

import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.service.UpmsUserService;
import com.xk.upms.usecase.user.UpmsUserDeleteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpmsUserDeleteUseCaseImpl implements UpmsUserDeleteUseCase {

    @Autowired
    private UpmsUserService upmsUserService;

    @Override
    public void delete(Long id) {
        UpmsUser upmsUser = upmsUserService.getOneById(id);
        upmsUserService.delete(upmsUser);
    }

}
