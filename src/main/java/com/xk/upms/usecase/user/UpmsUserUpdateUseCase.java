package com.xk.upms.usecase.user;

import com.xk.upms.model.bo.UpmsUserCreateReq;
import com.xk.upms.model.vo.UpmsUserResp;

public interface UpmsUserUpdateUseCase {

    UpmsUserResp update(Long id, UpmsUserCreateReq request);

}
