package com.demo.common.advisor;

import com.demo.common.enums.ErrorCode;
import com.demo.common.vo.response.ErrorResponse;
import com.demo.common.vo.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 사용자 정의 Exception
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    /**
     *
     * Exception들 추가 필요...
     * ...
     * ...
     * ...+
     */


    /**
     * 공통 Exception
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ErrorResponse.toResponseEntity(errorCode);
    }

}
