package com.xk.upms.usecase.user.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.model.bo.UpmsUserCreateReq;
import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.model.vo.UpmsUserResp;
import com.xk.upms.service.UpmsUserService;
import com.xk.upms.usecase.user.UpmsUserCreateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpmsUserCreateUseCaseImpl implements UpmsUserCreateUseCase {

    @Autowired
    private UpmsUserService upmsUserService;

    @Override
    public UpmsUserResp create(UpmsUserCreateReq request) {
        UpmsUser upmsUser = XkBeanUtils.copyProperties(request, UpmsUser::new);
        UpmsUser saveUpmsUser = upmsUserService.create(upmsUser);
        return XkBeanUtils.copyProperties(saveUpmsUser, UpmsUserResp::new);
    }

}
