package com.xk.upms.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xk.upms.model.po.UpmsUser;

/**
 * Repository interface for {@link UpmsUser} entities. Provides basic CRUD
 * operations, and supports custom queries.
 */
@Repository
public interface UpmsUserRepository extends JpaRepository<UpmsUser, Long>, JpaSpecificationExecutor<UpmsUser> {

	/**
	 * Finds a user by name.
	 *
	 * @param name the name of the user.
	 * @return an {@link Optional} containing the user, if found.
	 */
	Optional<UpmsUser> findByUsername(String username);

	/**
	 * Finds a user by email using a custom JPQL query.
	 *
	 * @param email the email of the user.
	 * @return an {@link Optional} containing the user, if found.
	 */
	@Query("SELECT u FROM UpmsUser u WHERE u.email = :email")
	Optional<UpmsUser> findByEmail(@Param("email") String email);

}
