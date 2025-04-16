package com.xk.upms.domain.model.bo;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class UpmsUserRoleBO {

    private Long id;

    private UUID uuid;

    private UUID userUuid;

    private UUID roleUuid;

}
