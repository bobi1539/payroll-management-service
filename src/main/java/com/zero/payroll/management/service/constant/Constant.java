package com.zero.payroll.management.service.constant;

import com.zero.payroll.management.service.exception.BusinessException;

public final class Constant {

    private Constant() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static final String SUCCESS = "Sukses";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String ERROR = "Error : {}";
}
