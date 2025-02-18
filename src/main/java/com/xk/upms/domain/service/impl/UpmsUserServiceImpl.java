package com.xk.upms.domain.service.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsUserRepository;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.model.bo.UpmsUserInitBO;
import com.xk.upms.domain.model.po.UpmsUser;
import com.xk.upms.domain.service.UpmsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 📌 `UserServiceImpl` - 使用者領域服務的具體實作
 * 
 * - **提供基本的 CRUD 操作**
 * - **支援條件查詢**
 * - **確保與 `Repository` 交互的邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/02/14 saveAllUsers().
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsUserServiceImpl implements UpmsUserService {

	private final UpmsUserRepository upmsUserRepository;

    /**
     * {@inheritDoc}
     * 
     * ✅ `save()` 應該直接回傳 `ExamplePO`
     * ✅ `findById()` 使用 `Optional`，確保呼叫端處理缺少的值
     */
	@Override
    @Transactional
    public UpmsUserBO save(UpmsUserBO userBO) {
		UpmsUserBO reslutBo = new UpmsUserBO();
		if (userBO == null) {
			throw new IllegalArgumentException("使用者不能為 null");
		}
    	log.info("📌 儲存使用者: {}", userBO.getUsername());
        UpmsUser userPO = XkBeanUtils.copyProperties(userBO, UpmsUser::new);
        userPO.setEnabled(true);
        userPO.setLocked(false);
        UpmsUser savedPO = upmsUserRepository.save(userPO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
        return reslutBo;
    }

    /**
     * {@inheritDoc}
     */
	@Override
    @Transactional
    public List<UpmsUserBO> saveAllUsers(List<UpmsUserInitBO> boList) {
        if (boList == null || boList.isEmpty()) {
            log.warn("⚠️ 空的使用者列表，不進行任何儲存操作");
            return Collections.emptyList();
        }
        
        List<UpmsUser> users = XkBeanUtils.copyListProperties(boList, UpmsUser::new);
        List<UpmsUser> savedUsers = upmsUserRepository.saveAll(users);
        return XkBeanUtils.copyListProperties(savedUsers, UpmsUserBO::new);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional(readOnly = true)
    public Optional<UpmsUserBO> findById(Long userId) {
        log.info("📌 查詢使用者 ID: {}", userId);
        return upmsUserRepository.findById(userId)
                .map(upmsUser -> new UpmsUserBO(
                        upmsUser.getId(),
                		upmsUser.getUsername(),
                		upmsUser.getEmail(),
                        upmsUser.getCellPhone(),
                        upmsUser.getPassword(),
                        upmsUser.getEnabled(),
                        upmsUser.getLocked()
                ));
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public Optional<UpmsUserBO> findByUsername(String username) {
        log.info("📌 查詢使用者，username: {}", username);
        return upmsUserRepository.findByUsername(username)
                .map(upmsUser -> new UpmsUserBO(
                        upmsUser.getId(),
                		upmsUser.getUsername(),
                		upmsUser.getEmail(),
                        upmsUser.getCellPhone(),
                        upmsUser.getPassword(),
                        upmsUser.getEnabled(),
                        upmsUser.getLocked()
                ));
    }

    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional(readOnly = true)
    public Page<UpmsUserBO> findAll(UpmsUserBO request, Pageable pageable) {
        if (request == null) {
            log.info("📌 查詢所有使用者 (分頁)");
            return upmsUserRepository.findAll(pageable)
                    .map(upmsUser -> new UpmsUserBO(
                            upmsUser.getId(),
                    		upmsUser.getUsername(),
                    		upmsUser.getEmail(),
                            upmsUser.getCellPhone(),
                            upmsUser.getPassword(),
                            upmsUser.getEnabled(),
                            upmsUser.getLocked()
                    ));            
		} else {
			log.info("📌 查詢所有使用者 (支援條件過濾 + 分頁)");
			
			Example<UpmsUser> example = buildExample(request);
			
			return upmsUserRepository.findAll(example, pageable)
					.map(upmsUser -> new UpmsUserBO(
                            upmsUser.getId(),
                            upmsUser.getUsername(),
                            upmsUser.getEmail(),
                            upmsUser.getCellPhone(),
                            upmsUser.getPassword(),
                            upmsUser.getEnabled(),
                            upmsUser.getLocked()
                    ));
		}
    }

    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional(readOnly = true)
	public List<UpmsUserBO> findAll(UpmsUserBO request, Sort sort) {
		log.info("📌 查詢所有使用者 (支援條件過濾)");
		if (request == null) {
		    return XkBeanUtils.copyListProperties(upmsUserRepository.findAll(sort), UpmsUserBO::new);
		}
		Example<UpmsUser> example = buildExample(request);
		return XkBeanUtils.copyListProperties(upmsUserRepository.findAll(example, sort), UpmsUserBO::new);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public UpmsUserBO update(Long userId, UpmsUserBO updateData) {
		UpmsUserBO reslutBo = new UpmsUserBO();
    	log.info("📌 儲存使用者: {}", updateData.getUsername());
        UpmsUser userPO = XkBeanUtils.copyProperties(updateData, UpmsUser::new);
        userPO.setId(userId);
        UpmsUser savedPO = upmsUserRepository.save(userPO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO, reslutBo);
        return reslutBo;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean delete(Long userId) {
        log.info("📌 嘗試刪除使用者 ID: {}", userId);
        return upmsUserRepository.findById(userId)
                .map(user -> {
                	upmsUserRepository.delete(user);
                    log.info("✅ 使用者 ID: {} 已刪除", userId);
                    return true;
                }).orElse(false);
    }

	private Example<UpmsUser> buildExample(UpmsUserBO request) {
	    ExampleMatcher matcher = ExampleMatcher.matching()
//	            .withIgnorePaths("email") // ✅ 忽略 `EmailVO`，避免 JPA 解析錯誤
	            .withIgnoreNullValues()
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
	            .withIgnoreCase();

	    return Example.of(XkBeanUtils.copyProperties(request, UpmsUser::new), matcher);
	}

}
