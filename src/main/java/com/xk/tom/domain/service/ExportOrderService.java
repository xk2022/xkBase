package com.xk.tom.domain.service;

import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.domain.model.bo.ExportOrderBO;

import java.text.ParseException;

public interface ExportOrderService {

    ExportOrderBO save (ExportOrderBO exportOrderBO) throws ParseException;

    ExportOrderResponseDTO getExportOrder(String orderid);
}
