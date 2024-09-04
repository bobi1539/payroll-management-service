package com.zero.payroll.management.constant;

import com.zero.payroll.management.exception.BusinessException;

public final class Endpoint {

    private Endpoint() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static final String BASE = "/api";
    public static final String POSITION = BASE + "/positions";
}
