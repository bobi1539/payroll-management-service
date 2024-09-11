package com.zero.payroll.management.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponse extends BaseEntityResponse {
    private Long id;
    private String fullName;
    private String address;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private Date joinDate;
    private PositionResponse position;
}
