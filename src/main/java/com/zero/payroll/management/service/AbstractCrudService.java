package com.zero.payroll.management.service;

import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.helper.PageHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

    protected static final String FIELD_ID = "id";

    protected void setCreatedBy(HeaderRequest header, MPosition position) {
        position.setCreatedBy(header.getUserId());
        position.setCreatedByName(header.getUserName());
    }

    protected void setUpdatedBy(HeaderRequest header, MPosition position) {
        position.setUpdatedBy(header.getUserId());
        position.setUpdatedByName(header.getUserName());
    }

    protected Sort sortByIdAsc() {
        return PageHelper.sortByColumnAsc(FIELD_ID);
    }

    protected Pageable pageableSortByIdAsc(PageDto pageDto) {
        Sort sort = PageHelper.sortByColumnAsc(FIELD_ID);
        return PageHelper.buildPageRequest(pageDto.getPage(), pageDto.getSize(), sort);
    }
}
