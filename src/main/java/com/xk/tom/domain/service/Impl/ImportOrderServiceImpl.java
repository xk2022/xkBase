package com.xk.tom.domain.service.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.aggreate.OrderId;
import com.xk.tom.domain.model.bo.ImportOrderBO;
import com.xk.tom.domain.repository.ImportOrderRepository;
import com.xk.tom.domain.repository.OrderIdRepository;
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
    private final OrderIdRepository orderIdRepository;

    @Override
    @Transactional
    public ImportOrderBO save(ImportOrderBO importOrderBO) {
        ImportOrderBO resultBo = new ImportOrderBO();
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String todaystr = new SimpleDateFormat("yyyyMMdd").format(today);
        log.info("📌 儲存訂單");
        Long maxSequence = orderIdRepository.findMaxSequenceBySeqDate(today);

        if (maxSequence == null) {
            maxSequence = 0L;
        }

        // 3. 新的 sequence
        Long newSequence = maxSequence + 1;
        String sequenceStr = String.format("%08d", newSequence);

        //寫入新流水號
        OrderId orderId = new OrderId();
        orderId.setSequence(sequenceStr);
        orderId.setSeqDate(todaystr);
        orderIdRepository.save(orderId);

        // 4. 建立新的 OrderId
        ImportOrderAggreate aggreate = XkBeanUtils.copyProperties(importOrderBO ,ImportOrderAggreate::new );
        aggreate.setOrderId(todaystr+"-"+sequenceStr);
        aggreate.setOrderType("IMPORT");
        aggreate.setStatus("PENDING");
        ImportOrderAggreate savedImportOrderAggreate  =importOrderRepository.save(aggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedImportOrderAggreate ,resultBo);

        return resultBo;
    }
}
