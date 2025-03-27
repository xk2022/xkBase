package com.xk.upms.domain.dao.repository;

import com.xk.upms.domain.model.po.UpmsRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpmsRolePermissionRepository  extends JpaRepository<UpmsRolePermission, Long>, JpaSpecificationExecutor<UpmsRolePermission> {

    @Query(value = """
    SELECT rp.permissionId FROM  UpmsRolePermission  rp WHERE rp.roleId IN :roleIds
    """)
    List<UpmsRolePermission> findPermissionIdsByRoleIds(List<Long> roleIds);
}
