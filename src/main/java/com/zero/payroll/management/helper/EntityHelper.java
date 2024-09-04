package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.response.BaseEntityResponse;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.entity.BaseEntity;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.exception.BusinessException;

public final class EntityHelper {

    private EntityHelper() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static void setBaseEntityResponse(BaseEntityResponse response, BaseEntity entity) {
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setCreatedByName(entity.getCreatedByName());
        response.setUpdatedByName(entity.getUpdatedByName());
        response.setDeleted(entity.isDeleted());
    }

    public static PositionResponse toPositionResponse(MPosition position) {
        PositionResponse response = PositionResponse.builder()
                .id(position.getId())
                .name(position.getName())
                .basicSalary(position.getBasicSalary())
                .build();
        setBaseEntityResponse(response, position);
        return response;
    }
}
