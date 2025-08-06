package com.xk.tom.application.model;

import com.xk.tom.domain.model.vo.ContainerNumber;
import lombok.Data;

/**
 *
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderUpdateCmd {
    private String shippingCompany;
    private ContainerNumber containerNumber;
}