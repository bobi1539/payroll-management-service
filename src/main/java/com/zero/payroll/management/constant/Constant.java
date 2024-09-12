package com.zero.payroll.management.constant;

import com.zero.payroll.management.exception.BusinessException;

public final class Constant {

    private Constant() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static final String SUCCESS = "Sukses";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String DATA_NOT_FOUND = "Data Not Found";
    public static final String WRONG_USERNAME_OR_PASSWORD = "Username atau Password Salah";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String ERROR = "Error : {}";
    public static final String AUTHORIZATION = "Authorization";
    public static final String POSITION_NAME_REQUIRED = "Nama Jabatan Tidak Boleh Kosong";
    public static final String BASIC_SALARY_REQUIRED = "Gaji Pokok Tidak Boleh Kosong";
    public static final String NAME_REQUIRED = "Nama Tidak Boleh Kosong";
    public static final String ADDRESS_REQUIRED = "Alamat Tidak Boleh Kosong";
    public static final String DATE_OF_BIRTH_REQUIRED = "Tanggal Lahir Tidak Boleh Kosong";
    public static final String PHONE_NUMBER_REQUIRED = "No Handphone Tidak Boleh Kosong";
    public static final String EMAIL_REQUIRED = "Email Tidak Boleh Kosong";
    public static final String JOIN_DATE_REQUIRED = "Tanggal Bergabung Tidak Boleh Kosong";
    public static final String POSITION_ID_REQUIRED = "Jabatan Tidak Boleh Kosong";
    public static final String USERNAME_REQUIRED = "Username Tidak Boleh Kosong";
    public static final String PASSWORD_REQUIRED = "Password Tidak Boleh Kosong";
}
