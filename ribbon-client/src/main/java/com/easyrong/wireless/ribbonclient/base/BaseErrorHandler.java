package com.easyrong.wireless.ribbonclient.base;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseErrorHandler {
    @ExceptionHandler(value = BaseException.class)
    public BaseRspEntity errorHandlerOverJson(BaseException exception) {
        BaseErrorInterface errorInfo = exception.getErrorInfo();
        BaseRspEntity result = new BaseRspEntity(errorInfo);
        return result;
    }
}
