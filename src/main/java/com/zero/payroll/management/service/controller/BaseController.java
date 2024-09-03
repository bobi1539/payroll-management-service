package com.zero.payroll.management.service.controller;

import com.zero.payroll.management.service.constant.GlobalMessage;
import com.zero.payroll.management.service.dto.response.BaseResponse;

public abstract class BaseController {

    protected <T> BaseResponse<T> buildSuccessResponse(T data) {
        return BaseResponse.<T>builder()
                .code(GlobalMessage.SUCCESS.status.value())
                .message(GlobalMessage.SUCCESS.message)
                .data(data)
                .build();
    }
}
