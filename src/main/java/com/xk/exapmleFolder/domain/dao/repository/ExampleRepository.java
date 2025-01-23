package com.xk.exapmleFolder.domain.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xk.exapmleFolder.domain.model.example.ExamplePO;

/**
 * 📌 UserRepository - 使用者倉儲（Repository）
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
public interface ExampleRepository extends JpaRepository<ExamplePO, Long> {

    // ✅ 依據 `username` 查詢使用者
	Optional<ExamplePO> findByUsername(String username);

	Optional<ExamplePO> findByEmail(String email);

}
