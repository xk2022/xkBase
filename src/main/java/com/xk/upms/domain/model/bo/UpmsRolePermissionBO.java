package com.xk.upms.domain.model.bo;


import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class UpmsRolePermissionBO {

    private Long id;

    private Long roleId;

    private Long permissionId;

    private UUID systemId;

    private Boolean active;

    private Boolean isDeleted = false;

    private String deleteUser;

    private ZonedDateTime deleteTime;

}
