package com.xk.exapmleFolder.domain.dao.repository;

import com.xk.exapmleFolder.domain.model.UserPO;
import com.xk.exapmleFolder.domain.model.example.ExamplePO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserPO, Long> {

    boolean existsByEmail(String email);

}