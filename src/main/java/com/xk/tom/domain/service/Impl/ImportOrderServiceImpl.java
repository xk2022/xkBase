package com.xk.tom.domain.service.Impl;

import com.xk.common.util.DateCoverUtils;
import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.aggreate.OrderId;
import com.xk.tom.domain.model.bo.ImportOrderBO;
import com.xk.tom.domain.repository.CustomerRepository;
import com.xk.tom.domain.repository.ImportOrderRepository;
import com.xk.tom.domain.repository.OrderIdRepository;
import com.xk.tom.domain.service.ImportOrderService;
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
public class ImportOrderServiceImpl implements ImportOrderService {

    private final ImportOrderRepository importOrderRepository;
    private final OrderIdRepository orderIdRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public ImportOrderBO save(ImportOrderBO importOrderBO) throws ParseException {
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
        orderId.setSequence(Long.parseLong(sequenceStr));
        orderId.setSeqDate(DateCoverUtils.StringCoverToDate(todaystr));
        orderIdRepository.save(orderId);

        // 4. 建立新的 OrderId
        ImportOrderAggreate aggreate = XkBeanUtils.copyProperties(importOrderBO ,ImportOrderAggreate::new );
        aggreate.setOrderId(todaystr+"-"+sequenceStr);
        ImportOrderAggreate savedImportOrderAggreate  =importOrderRepository.save(aggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedImportOrderAggreate ,resultBo);

        return resultBo;
    }

    @Override
    public Optional<ImportOrderAggreate> getImportOrder(String orderid) {

        return importOrderRepository.findByOrderId(orderid);

    }

    @Override
    @Transactional
    public int deleteImportOrder(Long importId) {
        try {
            importOrderRepository.deleteById(importId);
            return 1;
        } catch (Exception e) {
            log.error("刪除進口訂單失敗，importId: {}", importId, e);
            return 0;
        }
    }

    @Override
    public Optional<ImportOrderAggreate> findByImportIdAndStatusPending(Long importId) {
        return importOrderRepository.findByImportIdAndStatusPending(importId);
    }

    @Override
    public List<ImportOrderAggreate> getOrderByOrderTypeImport() {
        return importOrderRepository.findByOrderTypeImport();
    }

    @Override
    public List<ImportOrderAggreate> getOrderByCustomerNameAndOrderTypeImport(String customerName) {
        return importOrderRepository.findByCustomerNameAndOrderTypeImport(customerName);
    }

    @Override
    public List<ImportOrderAggreate> getImportOrderByKeyWord(String keyWord) {
        return importOrderRepository.findByImportOrderByKeyWord(keyWord);
    }

    @Override
    public ImportOrderAggreate updateImportOrder(Long importId) {

        return importOrderRepository.updateImportOrder(importId);
    }

    @Override
    public Optional<ImportOrderAggreate> findByImportId(Long importId) {
        return importOrderRepository.findById(importId);
    }

    @Override
    public ImportOrderAggreate update(ImportOrderAggreate importOrder) {
        return importOrderRepository.save(importOrder);
    }


}
