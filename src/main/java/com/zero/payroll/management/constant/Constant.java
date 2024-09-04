package com.zero.payroll.management.constant;

import com.zero.payroll.management.exception.BusinessException;

public final class Constant {

    private Constant() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static final String SUCCESS = "Sukses";
    public static final String DATA_NOT_FOUND = "Data Not Found";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String ERROR = "Error : {}";
    public static final String POSITION_NAME_REQUIRED = "Nama Jabatan Tidak Boleh Kosong";
    public static final String BASIC_SALARY_REQUIRED = "Gaji Pokok Tidak Boleh Kosong";
}
