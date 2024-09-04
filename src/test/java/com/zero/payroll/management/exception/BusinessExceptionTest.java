package com.zero.payroll.management.exception;

import com.zero.payroll.management.constant.GlobalMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void testCreateConstructor() {
        BusinessException e = new BusinessException(GlobalMessage.SUCCESS);
        assertEquals(GlobalMessage.SUCCESS.status, e.getStatus());
        assertEquals(GlobalMessage.SUCCESS.message, e.getMessage());
    }
}