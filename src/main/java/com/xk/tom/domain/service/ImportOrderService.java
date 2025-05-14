package com.xk.tom.domain.service;

import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.bo.ImportOrderBO;

import java.text.ParseException;
import java.util.Optional;

public interface ImportOrderService {

    ImportOrderBO save(ImportOrderBO importOrderBO)throws ParseException;

    ImportOrderResponseDTO getImportOrder(String orderid);

    int deleteImportOrder(Long importId);

    Optional<ImportOrderAggreate> findByImportIdAndStatusPending(Long importId);
}
