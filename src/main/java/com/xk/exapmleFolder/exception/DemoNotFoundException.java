package com.xk.exapmleFolder.exception;

/**
 * 
 * @author yuan Created on 2025/01/21.
 * @author yuan Updated on 2025/01/21 something note here.
 */
public class DemoNotFoundException extends RuntimeException {
	
	public DemoNotFoundException(Long orderId) {
        super("❌ 訂單 ID " + orderId + " 不存在");
    }

}
