package com.xk.upms.domain.model.bo;


import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class UpmsRolePermissionActionBO {

    private UUID uuid;

    private UUID roleUuid;

    private UUID permissionUuid;

    private UUID actionUuid;

    private Boolean active;

    private String deleteUser;

    private Boolean deleted;

    private ZonedDateTime deletedTime;

    private String createdBy;

    private ZonedDateTime createdTime;

    private String updatedBy;

    private ZonedDateTime updatedTime;

}
