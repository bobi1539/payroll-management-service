package com.zero.payroll.management.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PositionResponse extends BaseEntityResponse {
    private Long id;
    private String name;
    private Double basicSalary;
    private Double allowance;
}
