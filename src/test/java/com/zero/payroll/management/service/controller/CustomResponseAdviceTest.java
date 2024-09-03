package com.zero.payroll.management.service.controller;

import com.zero.payroll.management.service.constant.GlobalMessage;
import com.zero.payroll.management.service.dto.response.BaseResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CustomResponseAdviceTest {

    private final CustomResponseAdvice advice = new CustomResponseAdvice();

    @Test
    void testSupports() {
        boolean supports = advice.supports(any(), any());
        assertTrue(supports);
    }

    @Test
    void testBuildSuccessResponse() {
        BaseResponse<Object> response = advice.buildSuccessResponse(new Object());
        assertNotNull(response);
        assertEquals(GlobalMessage.SUCCESS.status.value(), response.getCode());
        assertEquals(GlobalMessage.SUCCESS.message, response.getMessage());
    }
}