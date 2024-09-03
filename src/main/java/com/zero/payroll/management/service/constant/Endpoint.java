package com.zero.payroll.management.service.constant;

import com.zero.payroll.management.service.exception.BusinessException;

public final class Endpoint {

    private Endpoint() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static final String BASE = "/api";
}
