package com.onz.common.web.advice;

import com.onz.common.enums.ErrorCode;
import com.onz.common.web.dto.response.ErrorResponse;
import com.onz.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 사용자 정의 Exception
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode(), e.getArgs());
    }

    /**
     *
     * Exception들 추가 필요...
     * ...
     * ...
     * ...+
     */

    /**
     *  InternalAuthenticationServiceException 인증 Exception
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> internalAuthenticationServiceException(InternalAuthenticationServiceException e, HttpServletRequest request){
        ResponseEntity<ErrorResponse> response;
        // Default 처리
        ErrorCode errorCode = ErrorCode.INVALID_AUTH_TOKEN;
        response = ErrorResponse.toResponseEntity(errorCode);

        // CustomException인 경우 처리
        if(e.getCause() instanceof CustomException){
            CustomException ce = (CustomException) e.getCause();
            response = ErrorResponse.toResponseEntity(ce.getErrorCode(), ce.getArgs());
        }
        return response;
    }
    /**
     * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        String bindResultCode = e.getBindingResult().getFieldError().getCode();
        ErrorCode errorCdoe = ErrorCode.valueOf(bindResultCode);

        return ErrorResponse.toResponseEntity(errorCdoe);
    }



    /**
     * 공통 Exception
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        if(ex.getMessage()!=null){
            return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR_DETAIL, ex.getMessage());
        }else {
            return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

}
