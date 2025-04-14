package com.xk.upms.domain.service;

import com.xk.common.util.dto.JwtUserDTO;
import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.model.po.UpmsUser;

public interface UpmsUserDetailsService {

    JwtUserDTO extract(UpmsUserBO upmsUserBO);

}
