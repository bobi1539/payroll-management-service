package com.zero.payroll.management.constant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum GlobalMessage {
    SUCCESS(HttpStatus.OK, Constant.SUCCESS),
    DATA_NOT_FOUND(HttpStatus.BAD_REQUEST, Constant.DATA_NOT_FOUND),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, Constant.INTERNAL_SERVER_ERROR);

    public final HttpStatus status;
    public final String message;
}
