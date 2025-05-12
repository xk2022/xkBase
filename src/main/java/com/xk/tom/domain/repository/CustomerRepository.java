package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.CustomerAggreate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerAggreate ,Long> {

    Optional<CustomerAggreate> findByContactPerson(String contactPerson);
}
