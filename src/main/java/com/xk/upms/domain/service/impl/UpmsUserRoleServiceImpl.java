package com.xk.upms.domain.service.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsUserRoleRepository;
import com.xk.upms.domain.model.bo.UpmsUserRoleBO;
import com.xk.upms.domain.model.po.UpmsUserRole;
import com.xk.upms.domain.service.UpmsUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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
        log.info("📌 儲存使用者角色: {}, {}", upmsUserRoleBO.getUserUuid(), upmsUserRoleBO.getRoleUuid());
        UpmsUserRole userRolePO = XkBeanUtils.copyProperties(upmsUserRoleBO, UpmsUserRole::new);
        UpmsUserRole savedPO = upmsUserRoleRepository.save(userRolePO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
        return reslutBo;
    }

    @Override
    public Optional<UpmsUserRoleBO> findByUserId(UUID userUuid) {
        return upmsUserRoleRepository.findByUserUuid(userUuid)
                .map(upmsUserRole -> new UpmsUserRoleBO(
                        upmsUserRole.getUuid(),
                        upmsUserRole.getUserUuid(),
                        upmsUserRole.getRoleUuid()
                ));
    }

    @Override
    public UpmsUserRoleBO update(UpmsUserRoleBO upmsUserRoleBO) {
        UpmsUserRoleBO resultBo = new UpmsUserRoleBO();
        UpmsUserRole userRolePO = XkBeanUtils.copyProperties(upmsUserRoleBO, UpmsUserRole::new);
        UpmsUserRole savedPO = upmsUserRoleRepository.save(userRolePO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, resultBo);
        return resultBo;
    }

}
