package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.exception.BusinessException;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public final class SpecificationHelper {

    private SpecificationHelper() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static <T> Specification<T> stringLike(String attribute, String value) {
        return (root, query, cb) -> {
            if (StringHelper.isEmpty(value)) {
                return null;
            }
            return cb.like(cb.lower(root.get(attribute)), StringHelper.queryLike(value.toLowerCase()));
        };
    }

    public static <T> Specification<T> objectEquals(String attribute, Object value) {
        return (root, query, cb) -> {
            if (Objects.isNull(value)) {
                return null;
            }
            return cb.equal(root.get(attribute), value);
        };
    }
}
