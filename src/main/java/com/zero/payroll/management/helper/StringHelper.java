package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.exception.BusinessException;

import java.text.MessageFormat;
import java.util.Objects;

public final class StringHelper {

    private StringHelper() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static boolean isEmpty(String string) {
        return Objects.isNull(string) || string.isEmpty();
    }

    public static String queryLike(String string) {
        return MessageFormat.format("%{0}%", string);
    }
}
