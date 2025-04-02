package com.xk.upms.domain.dao.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xk.upms.domain.model.po.UpmsUser;


/**
 * Repository interface for {@link UpmsUser} entities. Provides basic CRUD
 * operations, and supports custom queries.
 */
@Repository
public interface UpmsUserRepository extends JpaRepository<UpmsUser, Long>, JpaSpecificationExecutor<UpmsUser> {

	/**
	 * Finds a user by name.
	 *
	 * @param username the name of the user.
	 * @return an {@link Optional} containing the user, if found.
	 */
	Optional<UpmsUser> findByIsDeletedFalseAndUsername(String username);

	/**
	 * Finds a user by email using a custom JPQL query.
	 *
	 * @param email the email of the user.
	 * @return an {@link Optional} containing the user, if found.
	 */
	@Query("SELECT u FROM UpmsUser u WHERE u.email = :email")
	Optional<UpmsUser> findByIsDeletedFalseAndEmail(@Param("email") String email);

	@Query(value =
			"""
            SELECT
                u.id AS id,
                u.username AS username,
                u.email AS email,
                u.cellPhone AS cellPhone,
                ur.role.id AS roleId,
                u.enabled AS enabled,
                u.locked AS locked
            FROM
                UpmsUser u
            LEFT JOIN
                UpmsUserRole ur
            ON
                ur.upmsUser.id = u.id
            WHERE
            	1 = 1
            	AND u.isDeleted = false
            	AND
                (
                    (:keyword IS NULL OR u.username LIKE CONCAT('%', :keyword, '%')) OR
                    (:keyword IS NULL OR u.email LIKE CONCAT('%', :keyword, '%')) OR
                    (:keyword IS NULL OR u.cellPhone LIKE CONCAT('%', :keyword, '%'))
                )
                AND (:enabled IS NULL OR u.enabled = :enabled)
                AND (:locked IS NULL OR u.locked = :locked)
           	ORDER BY
           		u.id ASC,
           		u.username ASC
            """)
	List<Map<String, Object>> findAllLike(
			@Param("keyword") String keyword,
			@Param("enabled") Boolean enabled,
			@Param("locked") Boolean locked
	);

}
