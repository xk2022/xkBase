package com.xk.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 📌 當系統已被刪除時拋出的異常
 */
@ResponseStatus(HttpStatus.GONE) // 410 Gone
public class EntityAlreadyDeletedException extends RuntimeException {
    public EntityAlreadyDeletedException(String message) {
        super(message);
    }
}