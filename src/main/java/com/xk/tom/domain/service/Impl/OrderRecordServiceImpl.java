package com.xk.tom.domain.service.Impl;


import com.xk.common.util.XkBeanUtils;
import com.xk.tom.domain.model.aggreate.OrderRecordAggreate;
import com.xk.tom.domain.model.bo.OrderRecordBO;
import com.xk.tom.domain.repository.OrderRecordRepository;
import com.xk.tom.domain.service.OrderRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderRecordServiceImpl implements OrderRecordService {

    private final OrderRecordRepository orderRecordRepository;
    @Override
    public OrderRecordBO save(OrderRecordBO orderRecordBO) {
        OrderRecordBO recordBO = new OrderRecordBO();
        log.info("儲存訂單記錄");
        OrderRecordAggreate orderRecordAggreate = XkBeanUtils.copyProperties(orderRecordBO ,OrderRecordAggreate::new );
        OrderRecordAggreate savedOrderRecordAggreate = orderRecordRepository.save(orderRecordAggreate);
        XkBeanUtils.copyPropertiesAutoConvert(savedOrderRecordAggreate ,recordBO);
        return recordBO;
    }
}
