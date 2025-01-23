package com.xk.upms.usecase.user.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.model.bo.UpmsUserFindReq;
import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.model.vo.UpmsUserResp;
import com.xk.upms.service.UpmsUserService;
import com.xk.upms.usecase.user.UpmsUserFindUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpmsUserFindUseCaseImpl implements UpmsUserFindUseCase {

    @Autowired
    private UpmsUserService upmsUserService;

    @Override
    public List<UpmsUserResp> getList(UpmsUserFindReq request) {
        Example<UpmsUser> example = request == null ? null
                : Example.of(XkBeanUtils.copyProperties(request, UpmsUser::new),
                ExampleMatcher.matching().withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase());
        Sort sort = Sort.by(Sort.Order.asc("id"), Sort.Order.asc("username"));
        List<UpmsUser> upmsUsers = upmsUserService.getList(example, sort);
        return XkBeanUtils.copyListProperties(upmsUsers, UpmsUserResp::new);
    }

    @Override
    public UpmsUserResp getOneById(Long id) {
        UpmsUser upmsUser = upmsUserService.getOneById(id);
        return XkBeanUtils.copyProperties(upmsUser, UpmsUserResp::new);
    }

}
