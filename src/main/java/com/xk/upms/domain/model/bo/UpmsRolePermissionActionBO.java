package com.xk.upms.domain.model.bo;


import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class UpmsRolePermissionActionBO {

    private Long roleId;

    private Long permissionId;

    private Long actionId;

    private Boolean active;

    private Boolean isDeleted = false;

    private String deleteUser;

    private ZonedDateTime deleteTime;

}
