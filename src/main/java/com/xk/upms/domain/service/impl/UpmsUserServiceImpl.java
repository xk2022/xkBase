package com.xk.upms.domain.service.impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.common.util.XkNativeUtil;
import com.xk.upms.domain.dao.repository.UpmsUserRepository;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.model.bo.UpmsUserInitBO;
import com.xk.upms.domain.model.po.UpmsUser;
import com.xk.upms.domain.service.UpmsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public UpmsUserBO save(UpmsUserBO upmsUserBO) {
		UpmsUserBO reslutBo = new UpmsUserBO();
		if (upmsUserBO == null) {
			throw new IllegalArgumentException("使用者不能為 null");
		}
    	log.info("📌 儲存使用者: {}", upmsUserBO.getUsername());
        upmsUserRepository.findByIsdeletedFalseAndUsername(upmsUserBO.getUsername()).ifPresent(user -> {
            throw new IllegalArgumentException("使用者名稱重複");
        });
        upmsUserRepository.findByIsdeletedFalseAndEmail(upmsUserBO.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("信箱名稱重複");
        });
        UpmsUser userPO = XkBeanUtils.copyProperties(upmsUserBO, UpmsUser::new);
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
                        null,
                        upmsUser.getPassword(),
                        upmsUser.getEnabled(),
                        upmsUser.getLocked(),
                        upmsUser.getLastLogin(), 
                        upmsUser.getFailedAttempts(),
                        upmsUser.getIsdeleted(),
                        upmsUser.getDeleteUser(),
                        upmsUser.getDeleteTime()
                ));
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public Optional<UpmsUserBO> findByUsername(String username) {
        log.info("📌 查詢使用者，username: {}", username);
        return upmsUserRepository.findByIsdeletedFalseAndUsername(username)
                .map(upmsUser -> new UpmsUserBO(
                        upmsUser.getId(),
                		upmsUser.getUsername(),
                		upmsUser.getEmail(),
                        upmsUser.getCellPhone(),
                        null,
                        upmsUser.getPassword(),
                        upmsUser.getEnabled(),
                        upmsUser.getLocked(),
                        upmsUser.getLastLogin(),
                        upmsUser.getFailedAttempts(),
                        upmsUser.getIsdeleted(),
                        upmsUser.getDeleteUser(),
                        upmsUser.getDeleteTime()
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
                            null,
                            upmsUser.getPassword(),
                            upmsUser.getEnabled(),
                            upmsUser.getLocked(),
                            upmsUser.getLastLogin(),
                            upmsUser.getFailedAttempts(),
                            upmsUser.getIsdeleted(),
                            upmsUser.getDeleteUser(),
                            upmsUser.getDeleteTime()
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
                            null,
                            upmsUser.getPassword(),
                            upmsUser.getEnabled(),
                            upmsUser.getLocked(),
                            upmsUser.getLastLogin(),
                            upmsUser.getFailedAttempts(),
                            upmsUser.getIsdeleted(),
                            upmsUser.getDeleteUser(),
                            upmsUser.getDeleteTime()
                    ));
		}
    }

    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional(readOnly = true)
	public List<UpmsUserBO> findAllLike(String keyword, Boolean enabled, Boolean locked) {
		log.info("📌 查詢所有使用者 (支援條件過濾)");
        List<Map<String, Object>> resultBo = upmsUserRepository.findAllLike(keyword, enabled, locked);
        return XkNativeUtil.Convert(resultBo, UpmsUserBO.class);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public UpmsUserBO update(Long userId, UpmsUserBO updateData) {
		UpmsUserBO reslutBo = new UpmsUserBO();
    	log.info("📌 儲存使用者: {}", updateData.getUsername());
        upmsUserRepository.findByIsdeletedFalseAndUsername(updateData.getUsername()).ifPresent(user -> {
            if(!user.getId().equals(updateData.getId())){
                throw new IllegalArgumentException("使用者名稱重複");
            }
        });
        upmsUserRepository.findByIsdeletedFalseAndEmail(updateData.getEmail()).ifPresent(user -> {
            if(!user.getId().equals(updateData.getId())){
                throw new IllegalArgumentException("信箱名稱重複");
            }
        });
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
