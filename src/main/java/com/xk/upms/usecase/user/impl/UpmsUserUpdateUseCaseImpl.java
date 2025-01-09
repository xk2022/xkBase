package com.xk.upms.usecase.user.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.common.util.XkGenericUpdateService;
import com.xk.upms.model.bo.UpmsUserCreateReq;
import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.model.vo.UpmsUserResp;
import com.xk.upms.service.UpmsUserService;
import com.xk.upms.usecase.user.UpmsUserUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpmsUserUpdateUseCaseImpl implements UpmsUserUpdateUseCase {

    @Autowired
    private UpmsUserService upmsUserService;

    @Override
    public UpmsUserResp update(Long id, UpmsUserCreateReq request) {
        UpmsUser upmsUser = upmsUserService.getOneById(id);
        UpmsUser saveUpmsUser = upmsUserService.update(setUpdate(upmsUser, request));
        return XkBeanUtils.copyProperties(saveUpmsUser, UpmsUserResp::new);
    }

    private UpmsUser setUpdate(UpmsUser upmsUser, UpmsUserCreateReq request){
        XkGenericUpdateService<UpmsUser> updateService = new XkGenericUpdateService<>();
        return updateService.updateEntity(upmsUser, request);
    }

}
