package com.xk.tom.domain.service;


import com.xk.tom.domain.model.aggreate.CustomerAggreate;
import com.xk.tom.domain.model.bo.CustomerBO;

import java.util.Optional;

public interface CustomerService {

    CustomerBO save(CustomerBO customerBo);

    Optional<CustomerAggreate> findByContactPerson(String contactPerson);
}
