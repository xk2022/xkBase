package com.xk.upms.usecase.user;

import com.xk.upms.model.bo.UpmsUserCreateReq;
import com.xk.upms.model.vo.UpmsUserResp;

public interface UpmsUserCreateUseCase {

    UpmsUserResp create(UpmsUserCreateReq request);

}
