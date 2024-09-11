package com.zero.payroll.management.dto.request;

import com.zero.payroll.management.constant.Constant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PositionRequest {

    @NotNull(message = Constant.POSITION_NAME_REQUIRED)
    @NotEmpty(message = Constant.POSITION_NAME_REQUIRED)
    private String name;

    @NotNull(message = Constant.BASIC_SALARY_REQUIRED)
    private Double basicSalary;

    private Double allowance;
}
