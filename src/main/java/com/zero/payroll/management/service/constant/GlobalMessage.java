package com.zero.payroll.management.service.constant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum GlobalMessage {
    SUCCESS(HttpStatus.OK, Constant.SUCCESS),
    INTERNAL_SERVER_ERROR(HttpStatus.OK, Constant.INTERNAL_SERVER_ERROR);

    public final HttpStatus status;
    public final String message;
}
