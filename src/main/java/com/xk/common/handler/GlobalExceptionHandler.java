package com.xk.common.handler;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.xk.common.base.BaseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 📌 全局異常處理 - 負責捕獲並統一處理 Controller 層的異常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 🛑 自定義業務異常（BusinessException）
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResult<Object>> handleBusinessException(BusinessException ex) {
        log.warn("⚠️ [業務異常] {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResult.failure(HttpStatus.BAD_REQUEST, ex.getMessage(), null));
    }

    /**
     * 🛑 UUID 格式錯誤處理
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResult<String>> handleUUIDFormatException(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() != null && ex.getRequiredType().equals(UUID.class)) {
            log.warn("❌ [UUID 格式錯誤] {}", ex.getValue());
            return ResponseEntity.badRequest()
                    .body(BaseResult.failure(HttpStatus.BAD_REQUEST, "UUID 格式錯誤，請提供合法的 UUID", ex.getValue().toString()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResult.failure(HttpStatus.BAD_REQUEST, "請求參數類型錯誤", null));
    }

    /**
     * 🛑 資料庫約束違反（唯一性、外鍵等）
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResult<String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "❌ 資料違反唯一性約束";

        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException sqlEx =
                    (org.hibernate.exception.ConstraintViolationException) ex.getCause();
            message = "❌ 資料庫約束錯誤：" + sqlEx.getConstraintName();
        }

        log.warn("⚠️ [資料庫異常] {}", message);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(BaseResult.failure(HttpStatus.CONFLICT, message, null));
    }

    @ExceptionHandler(EntityAlreadyDeletedException.class)
    public ResponseEntity<String> handleAlreadyDeletedException(EntityAlreadyDeletedException ex) {
        return ResponseEntity.status(HttpStatus.GONE).body(ex.getMessage());
    }
    
    /**
     * 🛑 其他未知錯誤
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResult<String>> handleException(Exception ex) {
        log.error("❌ [未知錯誤] {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "系統錯誤，請聯繫管理員", null));
    }
}
