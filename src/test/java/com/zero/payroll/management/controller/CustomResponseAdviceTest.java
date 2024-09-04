package com.zero.payroll.management.controller;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.response.BaseResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CustomResponseAdviceTest {

    private final CustomResponseAdvice advice = new CustomResponseAdvice();

    @Test
    void testBuildSuccessResponse() {
        BaseResponse<Object> response = advice.buildSuccessResponse(new Object());
        assertNotNull(response);
        assertEquals(GlobalMessage.SUCCESS.status.value(), response.getCode());
        assertEquals(GlobalMessage.SUCCESS.message, response.getMessage());
    }
}