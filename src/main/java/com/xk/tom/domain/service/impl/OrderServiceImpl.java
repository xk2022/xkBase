package com.xk.tom.domain.service.impl;

import com.xk.tom.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final OrderRepository orderRepository;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public OrderPo findByUuid(UUID uuid) {
//        return orderRepository.findByUuid(uuid)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public List<OrderPo> findByStatus(OrderStatus status) {
//        return orderRepository.findByStatus(status);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public List<OrderPo> findAll() {
//        return orderRepository.findAll();
//    }
//
//    @Override
//    public void updateStatus(UUID uuid, OrderStatus newStatus) {
//
//    }

}