package com.xk.common.base;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的 API 回應封裝類。
 *
 * @param <T> 回應數據的類型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {

	/**
	 * 狀態碼，例如：200（成功），400（用戶錯誤），500（伺服器錯誤）
	 */
	private int code;

	/**
	 * 回應消息，可用於簡要描述操作結果
	 */
	private String message;

	/**
	 * 回應的數據內容
	 */
	private T data;

	/**
	 * 錯誤詳情，用於返回詳細的錯誤信息（可選）
	 */
	private Object errorDetails;

	/**
	 * 回應時間戳
	 */
	private LocalDateTime timestamp = LocalDateTime.now();

	/**
	 * 快速構造成功的回應
	 *
	 * @param data    回應數據
	 * @param message 成功消息
	 * @param <T>     數據類型
	 * @return 成功的 BaseResult 實例
	 */
	public static <T> BaseResult<T> success(T data, String message) {
	    return new BaseResult<>(HttpStatus.OK.value(), message, data, null, LocalDateTime.now());
	}

	/**
	 * 快速構造失敗的回應
	 *
	 * @param code         錯誤碼
	 * @param message      錯誤消息
	 * @param errorDetails 錯誤詳情（可為 null）
	 * @param <T>          數據類型
	 * @return 失敗的 BaseResult 實例
	 */
	public static <T> BaseResult<T> failure(HttpStatus status, String message, Object errorDetails) {
	    return new BaseResult<>(status.value(), message, null, errorDetails, LocalDateTime.now());
	}

}
