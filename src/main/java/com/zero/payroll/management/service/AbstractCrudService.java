package com.zero.payroll.management.service;

import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.entity.BaseEntity;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.helper.PageHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class AbstractCrudService<T, R> implements CrudService<T, R> {

    protected static final String FIELD_ID = "id";

    protected void setCreatedBy(BaseEntity entity, HeaderRequest header) {
        entity.setCreatedBy(header.getUserId());
        entity.setCreatedByName(header.getUserName());
    }

    protected void setUpdatedBy(BaseEntity entity, HeaderRequest header) {
        entity.setUpdatedBy(header.getUserId());
        entity.setUpdatedByName(header.getUserName());
    }

    protected Sort sortByIdAsc() {
        return PageHelper.sortByColumnAsc(FIELD_ID);
    }

    protected Pageable pageableSortByIdAsc(PageDto pageDto) {
        Sort sort = PageHelper.sortByColumnAsc(FIELD_ID);
        return PageHelper.buildPageRequest(pageDto.getPage(), pageDto.getSize(), sort);
    }
}
