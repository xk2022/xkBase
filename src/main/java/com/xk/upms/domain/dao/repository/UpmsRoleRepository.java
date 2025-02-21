package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpmsRoleRepository extends JpaRepository<UpmsRole, Long>, JpaSpecificationExecutor<UpmsRole> {
    Optional<UpmsRole> findByIsdeletedFalseAndCode(String code);

    @Query(value =
            """
            SELECT
                ur
            FROM
                UpmsRole ur
            WHERE
                1 = 1
                AND ur.isdeleted = false
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

}
