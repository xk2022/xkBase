package com.xk.upms.usecase.user;

import com.xk.upms.model.bo.UpmsUserFindReq;
import com.xk.upms.model.vo.UpmsUserResp;

import java.util.List;

public interface UpmsUserFindUseCase {

    List<UpmsUserResp> getList(UpmsUserFindReq request);

    UpmsUserResp getOneById(Long id);

}
