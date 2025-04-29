package com.xk.tom.domain.service.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.aggreate.OrderId;
import com.xk.tom.domain.model.bo.ImportOrderBO;
import com.xk.tom.domain.repository.ImportOrderRepository;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderServiceImpl implements ImportOrderService {

    private final ImportOrderRepository importOrderRepository;

    @Override
    @Transactional
    public ImportOrderBO save(ImportOrderBO importOrderBO) {
        ImportOrderBO resultBo = new ImportOrderBO();
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String todaystr = new SimpleDateFormat("yyyyMMdd").format(today);
        log.info("📌 儲存訂單");
        Long maxSequence = importOrderRepository.findMaxSequenceByDate(today);

        if (maxSequence == null) {
            maxSequence = 0L;
        }

        // 3. 新的 sequence
        Long newSequence = maxSequence + 1;
        String sequenceStr = String.format("%08d", newSequence);

        // 4. 建立新的 OrderId
        OrderId newOrderId = new OrderId(todaystr, sequenceStr);
        newOrderId.getFormattedOrderId();
        ImportOrderAggreate aggreate = XkBeanUtils.copyProperties(importOrderBO ,ImportOrderAggreate::new );
        aggreate.setOrderId(newOrderId);
        aggreate.setOrderType("IMPORT");
        aggreate.setStatus("PENDING");
        ImportOrderAggreate savedImportOrderAggreate  =importOrderRepository.save(aggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedImportOrderAggreate ,resultBo);

        return resultBo;
    }
}
