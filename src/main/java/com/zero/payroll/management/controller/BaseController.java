package com.zero.payroll.management.controller;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.response.BaseResponse;

public abstract class BaseController {

    protected <T> BaseResponse<T> buildSuccessResponse(T data) {
        return BaseResponse.<T>builder()
                .code(GlobalMessage.SUCCESS.status.value())
                .message(GlobalMessage.SUCCESS.message)
                .data(data)
                .build();
    }

    protected SearchDto buildSearchDto(String search) {
        return SearchDto.builder().search(search).build();
    }

    protected PageDto buildPageDto(String search, int page, int size) {
        return PageDto.builder()
                .search(search)
                .page(page)
                .size(size)
                .build();
    }
}
