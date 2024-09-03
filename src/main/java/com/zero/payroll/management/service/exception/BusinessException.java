package com.zero.payroll.management.service.exception;

import com.zero.payroll.management.service.constant.GlobalMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public BusinessException(GlobalMessage globalMessage) {
        super(globalMessage.message);
        this.status = globalMessage.status;
        this.message = globalMessage.message;
    }
}
