package com.xk.tom.domain.model.bo;


import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class CustomerBO {

    private Long customerId;
    private String customerName;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String taxId;
    private String address;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;

}
