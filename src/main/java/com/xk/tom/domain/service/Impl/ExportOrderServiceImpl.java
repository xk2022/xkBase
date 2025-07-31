package com.xk.tom.domain.service.Impl;


import com.xk.common.util.DateCoverUtils;
import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;
import com.xk.tom.domain.model.aggreate.OrderId;
import com.xk.tom.domain.model.aggreate.OrderTypeEnum;
import com.xk.tom.domain.model.bo.ExportOrderBO;
import com.xk.tom.domain.repository.ExportOrderRepository;
import com.xk.tom.domain.repository.OrderIdRepository;
import com.xk.tom.domain.service.ExportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportOrderServiceImpl implements ExportOrderService {


    private final ExportOrderRepository exportOrderRepository;
    private final OrderIdRepository orderIdRepository;


    @Override
    @Transactional
    public ExportOrderBO save(ExportOrderBO exportOrderBO) throws ParseException {
        ExportOrderBO resultBO = new ExportOrderBO();
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
        orderId.setSequence(Long.parseLong(sequenceStr));
        orderId.setSeqDate(DateCoverUtils.StringCoverToDate(todaystr));
        orderIdRepository.save(orderId);


        ExportOrderAggreate exportOrderAggreate = XkBeanUtils.copyProperties(exportOrderBO, ExportOrderAggreate::new);
        exportOrderAggreate.setOrderId(todaystr + "-" + sequenceStr);
        ExportOrderAggreate savedExportOrderAggreate = exportOrderRepository.save(exportOrderAggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedExportOrderAggreate, resultBO);
        return resultBO;
    }

    @Override
    public ExportOrderResponseDTO getExportOrder(String orderid) {
        Optional<ExportOrderAggreate> aggreateOptional = exportOrderRepository.findByOrderId(orderid);
        ExportOrderResponseDTO responseDTO;
        if (aggreateOptional.isPresent()) {
            ExportOrderAggreate exportOrderAggreate = aggreateOptional.get();
            responseDTO = XkBeanUtils.copyProperties(exportOrderAggreate, ExportOrderResponseDTO::new);
            return responseDTO;
        }
        return null;
    }

    @Override
    public Optional<ExportOrderAggreate> findByImportIdAndStatusPending(Long exportId) {
        return exportOrderRepository.findByImportIdAndStatusPending(exportId);
    }

    @Override
    public List<ExportOrderAggreate> getOrderByOrderTypeImport() {
        return exportOrderRepository.findByOrderTypeExport();
    }

    @Override
    public List<ExportOrderAggreate> getOrderByCustomerNameAndOrderTypeExport(String customerName) {
        return exportOrderRepository.findByCustomerNameAndOrderTypeExport(customerName);
    }

    @Override
    public Optional<ExportOrderAggreate> findByExportId(Long exportId) {
        return exportOrderRepository.findById(exportId);
    }

    @Override
    public ExportOrderAggreate update(ExportOrderAggreate exportOrderAggreate) {
        return exportOrderRepository.save(exportOrderAggreate);

    }

    @Override
    public List<ExportOrderAggreate> getImportOrderByKeyWord(String keyWord) {
        return exportOrderRepository.findByExportOrderByKeyWord(keyWord);
    }
}
