package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.exception.BusinessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PageHelper {

    private PageHelper() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static Sort sortByColumnAsc(String columnName) {
        return Sort.by(columnName).ascending();
    }

    public static Sort sortByColumnDesc(String columnName) {
        return Sort.by(columnName).descending();
    }

    public static PageRequest buildPageRequest(int pageNumber, int pageSize, Sort sort) {
        return PageRequest.of(getPageNumber(pageNumber), pageSize, sort);
    }

    public static int getPageNumber(int pageNumber) {
        if (pageNumber == 0) {
            return pageNumber;
        }
        return pageNumber - 1;
    }
}
