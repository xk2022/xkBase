package com.xk.tom.domain.service.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.domain.model.aggreate.CustomerAggreate;
import com.xk.tom.domain.model.bo.CustomerBO;
import com.xk.tom.domain.repository.CustomerRepository;
import com.xk.tom.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerBO save(CustomerBO customerBo) {
        if(customerBo == null){
            throw new IllegalArgumentException("客戶(貨主)資料不能為 null");
        }
        CustomerBO resultBO = new CustomerBO();
        // 檢核名稱是否重複
        customerRepository.findByContactPerson(customerBo.getContactPerson()).ifPresent(role -> {
            throw new IllegalArgumentException("客戶(貨主)資料重複");
        });
        CustomerAggreate customerAggreate = XkBeanUtils.copyProperties(customerBo ,CustomerAggreate::new);
        CustomerAggreate savedCustomerAggreate = customerRepository.save(customerAggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedCustomerAggreate,resultBO);
        return resultBO;
    }

    @Override
    public Optional<CustomerAggreate> findByContactPerson(String contactPerson) {
        return customerRepository.findByContactPerson(contactPerson);
    }
}
