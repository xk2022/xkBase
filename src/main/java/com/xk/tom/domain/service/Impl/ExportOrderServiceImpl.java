package com.xk.tom.domain.service.Impl;


import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;
import com.xk.tom.domain.model.aggreate.OrderId;
import com.xk.tom.domain.model.bo.ExportOrderBO;
import com.xk.tom.domain.repository.ExportOrderRepository;
import com.xk.tom.domain.service.ExportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportOrderServiceImpl implements ExportOrderService {


    private final ExportOrderRepository exportOrderRepository;


    @Override
    @Transactional
    public ExportOrderBO save(ExportOrderBO exportOrderBO) {
        ExportOrderBO resultBO = new ExportOrderBO();
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String todaystr = new SimpleDateFormat("yyyyMMdd").format(today);
        log.info("📌 儲存訂單");
        Long maxSequence = exportOrderRepository.findMaxSequenceByDate(today);

        if (maxSequence == null) {
            maxSequence = 0L;
        }

        // 3. 新的 sequence
        Long newSequence = maxSequence + 1;
        String sequenceStr = String.format("%08d", newSequence);

        // 4. 建立新的 OrderId
        OrderId newOrderId = new OrderId(todaystr, sequenceStr);
        newOrderId.getFormattedOrderId();

        ExportOrderAggreate exportOrderAggreate = XkBeanUtils.copyProperties(exportOrderBO ,ExportOrderAggreate::new);
        exportOrderAggreate.setOrderId(newOrderId);
        exportOrderAggreate.setOrderType("EXPORT");
        exportOrderAggreate.setStatus("PENDING");
        ExportOrderAggreate savedExportOrderAggreate = exportOrderRepository.save(exportOrderAggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedExportOrderAggreate ,resultBO);
        return resultBO;
    }
}
