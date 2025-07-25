package com.xk.tom.domain.service;

import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.bo.ImportOrderBO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ImportOrderService {

    ImportOrderBO save(ImportOrderBO importOrderBO)throws ParseException;

    Optional<ImportOrderAggreate> getImportOrder(String orderid);

    int deleteImportOrder(Long importId);

    Optional<ImportOrderAggreate> findByImportIdAndStatusPending(Long importId);

    List<ImportOrderAggreate> getOrderByOrderTypeImport();

    List<ImportOrderAggreate> getOrderByCustomerNameAndOrderTypeImport(String customerName);

    List<ImportOrderAggreate> getImportOrderByKeyWord(String keyWord);

    ImportOrderAggreate updateImportOrder(Long importId);

    Optional<ImportOrderAggreate> findByImportId(Long importId);

    ImportOrderAggreate update(ImportOrderAggreate importOrder);
}
