package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.helper.ObjectDummy;

public abstract class ServiceTest {

    protected final Long id = 1L;
    protected SearchDto searchDto = ObjectDummy.getSearchDto();
    protected PageDto pageDto = ObjectDummy.getPageDto();
    protected HeaderRequest header = ObjectDummy.getHeaderRequest();
}
