package com.xk.tom.domain.service;

import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.domain.model.bo.ImportOrderBO;

import java.text.ParseException;

public interface ImportOrderService {

    ImportOrderBO save(ImportOrderBO importOrderBO)throws ParseException;

    ImportOrderResponseDTO getImportOrder(String orderid);
}
