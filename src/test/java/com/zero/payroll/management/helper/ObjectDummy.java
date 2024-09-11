package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.dto.request.PositionRequest;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.exception.BusinessException;

public final class ObjectDummy {

    private ObjectDummy() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static SearchDto getSearchDto() {
        return SearchDto.builder().search("").build();
    }

    public static PageDto getPageDto() {
        return PageDto.builder().search("").page(1).size(10).build();
    }

    public static HeaderRequest getHeaderRequest() {
        return HeaderRequest.builder().userId(1L).userName("Ucup").build();
    }

    public static MPosition getPosition() {
        return MPosition.builder().id(1L).name("Manager").basicSalary(10_000_000D).allowance(500_000D).build();
    }

    public static PositionRequest getPositionRequest() {
        return PositionRequest.builder().name("Manager").basicSalary(10_000_000D).allowance(500_000D).build();
    }

    public static PositionResponse getPositionResponse() {
        return EntityHelper.toPositionResponse(getPosition());
    }
}
