package com.xk.upms.domain.service.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsRoleRepository;
import com.xk.upms.domain.dao.repository.UpmsUserRoleRepository;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.model.bo.UpmsUserRoleBO;
import com.xk.upms.domain.model.po.UpmsUserRole;
import com.xk.upms.domain.service.UpmsUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpmsUserRoleServiceImpl implements UpmsUserRoleService {

    private final UpmsUserRoleRepository upmsUserRoleRepository;

    @Override
    @Transactional
    public UpmsUserRoleBO save(UpmsUserRoleBO upmsUserRoleBO) {
        UpmsUserRoleBO reslutBo = new UpmsUserRoleBO();
        if (upmsUserRoleBO == null) {
            throw new IllegalArgumentException("使用者不能為 null");
        }
        log.info("📌 儲存使用者角色: {}, {}", upmsUserRoleBO.getUserId(), upmsUserRoleBO.getRoleId());
        UpmsUserRole userRolePO = XkBeanUtils.copyProperties(upmsUserRoleBO, UpmsUserRole::new);
        UpmsUserRole savedPO = upmsUserRoleRepository.save(userRolePO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
        return reslutBo;
    }

    @Override
    public List<UpmsUserRoleBO> findByUserIdIn(List<Long> userId) {
        return XkBeanUtils.copyListProperties(upmsUserRoleRepository.findByUserIdIn(userId), UpmsUserRoleBO::new);
    }

}
