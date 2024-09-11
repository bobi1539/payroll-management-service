package com.zero.payroll.management.dto.request;

import com.zero.payroll.management.constant.Constant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeRequest {

    @NotNull(message = Constant.NAME_REQUIRED)
    @NotEmpty(message = Constant.NAME_REQUIRED)
    private String fullName;

    @NotNull(message = Constant.ADDRESS_REQUIRED)
    @NotEmpty(message = Constant.ADDRESS_REQUIRED)
    private String address;

    @NotNull(message = Constant.DATE_OF_BIRTH_REQUIRED)
    private Date dateOfBirth;

    @NotNull(message = Constant.PHONE_NUMBER_REQUIRED)
    @NotEmpty(message = Constant.PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    @NotNull(message = Constant.EMAIL_REQUIRED)
    @NotEmpty(message = Constant.EMAIL_REQUIRED)
    private String email;

    @NotNull(message = Constant.JOIN_DATE_REQUIRED)
    private Date joinDate;

    @NotNull(message = Constant.POSITION_ID_REQUIRED)
    private Long positionId;
}
