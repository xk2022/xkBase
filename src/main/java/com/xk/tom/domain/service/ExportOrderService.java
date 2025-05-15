package com.xk.tom.domain.service;

import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;
import com.xk.tom.domain.model.bo.ExportOrderBO;

import java.text.ParseException;
import java.util.Optional;

public interface ExportOrderService {

    ExportOrderBO save (ExportOrderBO exportOrderBO) throws ParseException;

    ExportOrderResponseDTO getExportOrder(String orderid);

    Optional<ExportOrderAggreate> findByImportIdAndStatusPending(Long exportId);
}
