package com.xk.common.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xk.common.base.BaseResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler {

	private final HttpServletRequest request;

	/**
	 * 請求標頭錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResult.failure(HttpStatus.BAD_REQUEST, "Header Missing", ex.getMessage()));
	}

	/**
	 * 請求錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(BaseResult.failure(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed", ex.getMessage()));
	}

	/**
	 * 請求的媒體類型錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(BaseResult.failure(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "UnSupport Media Type", ex.getMessage()));
	}

	/**
	 * 請求錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResult.failure(HttpStatus.BAD_REQUEST, "Http Message Not Readable", ex.getMessage()));
	}

	/**
	 * 請求驗證錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleBindException(BindException ex) {
		List<String> messages = ex.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResult.failure(HttpStatus.BAD_REQUEST, String.join("; ", messages), ex.getMessage()));
	}

	/**
	 * 參數錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UnexpectedTypeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleUnexpectedTypeException(UnexpectedTypeException ex) {
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResult.failure(HttpStatus.BAD_REQUEST, "參數錯誤", ex.getMessage()));
	}

	/**
	 * 參數驗證失敗
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("Validation error", ex);
		// 取得所有錯誤訊息，並格式化
		List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()) // 取出
																														// DTO
																														// 設定的
																														// message
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResult.failure(HttpStatus.BAD_REQUEST, "請求參數錯誤", messages));
	}

	/**
	 * 系統錯誤
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleException(Exception ex) {
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(BaseResult.failure(HttpStatus.INTERNAL_SERVER_ERROR, "System Error", ex.getMessage()));
	}

}
