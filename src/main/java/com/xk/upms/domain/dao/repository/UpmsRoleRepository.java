package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UpmsRoleRepository extends JpaRepository<UpmsRole, UUID>, JpaSpecificationExecutor<UpmsRole> {

    Optional<UpmsRole> findByDeletedFalseAndUuid(UUID uuid);

    Optional<UpmsRole> findByDeletedFalseAndCode(String code);

    @Query(value =
            """
            SELECT
                ur
            FROM
                UpmsRole ur
            WHERE
                1 = 1
                AND ur.deleted = false
                AND
                (
                    (:keyword IS NULL OR ur.code LIKE CONCAT('%', :keyword, '%')) OR
                    (:keyword IS NULL OR ur.title LIKE CONCAT('%', :keyword, '%'))
                )
            ORDER BY
                ur.orders ASC,
                ur.id ASC
            """)
    List<UpmsRole> findAllLike(@Param("keyword") String keyword);



    @Query(value =
            """
            SELECT
                r
            FROM 
                UpmsRole r
            RIGHT JOIN
                UpmsUserRole ur
            ON
                ur.roleUuid = r.uuid
            WHERE
                ur.userUuid = :userUuid
            """)
    Optional<UpmsRole> findUserRoleByUserUuid(@Param("userUuid") UUID userUuid);

}
