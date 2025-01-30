package com.xk.upms.service.impl;

import com.xk.upms.dao.repository.UpmsUserRepository;
import com.xk.upms.model.po.UpmsUser;
import com.xk.upms.service.UpmsUserService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link UpmsUserService} interface.
 * <p>
 * This class provides concrete implementations for managing UPMS users,
 * including creating, retrieving, updating, and deleting user records.
 * </p>
 *
 * @author yuan
 * @version 1.1, Created on 2024/12/06.
 */
@Service
public class UpmsUserServiceImpl implements UpmsUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

	@Autowired
	private UpmsUserRepository upmsUserRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UpmsUser> getList(Example<UpmsUser> example, Sort sort) {
		return (example == null) ? upmsUserRepository.findAll(sort) : upmsUserRepository.findAll(example, sort);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public UpmsUser getOneById(Long id) {
		return upmsUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UpmsUser with ID " + id + " not found"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUser create(UpmsUser upmsUser) {
		return upmsUserRepository.save(upmsUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UpmsUser update(UpmsUser upmsUser) {
		return upmsUserRepository.save(upmsUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(UpmsUser upmsUser) {
		upmsUserRepository.delete(upmsUser);
	}

}
