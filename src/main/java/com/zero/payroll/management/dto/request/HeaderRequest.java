package com.zero.payroll.management.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HeaderRequest {
    private Long userId;
    private String userName;
}
