package com.xk.upms.domain.model.bo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class UpmsUserRoleBO {

    private Long id;

    private Long userId;

    private Long roleId;

}
