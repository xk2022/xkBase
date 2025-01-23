package com.xk.exapmleFolder.infrastructure.event.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.xk.exapmleFolder.domain.event.DemoPlacedEvent;

/**
 * OrderEventHandler
 * 
 * @param event
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
@Component
public class DemoEventHandler {

	@EventListener
	public void handleOrderPlacedEvent(DemoPlacedEvent event) {
		System.out.println("📢 訂單已創建：" + event.getOrderId()); // ✅ 監聽並處理事件

		// 這裡可以通知「庫存系統」、「支付系統」等
	}

}
