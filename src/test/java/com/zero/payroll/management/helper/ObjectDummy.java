package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.JwtComponentDto;
import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.request.EmployeeRequest;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.dto.request.LoginRequest;
import com.zero.payroll.management.dto.request.PositionRequest;
import com.zero.payroll.management.dto.response.EmployeeResponse;
import com.zero.payroll.management.dto.response.LoginResponse;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.entity.MEmployee;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.entity.MUser;
import com.zero.payroll.management.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ObjectDummy {


    private ObjectDummy() {
        throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
    }

    public static final String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE" +
            "3MjYxMDUyMDgsImV4cCI6MTc1NzY0MTIwOH0.GCHe-PZy5ES38A8lDxuDkHbxWSM6ZAlkmzMpMrR8HFk";
    public static final String JWT_SECRET = "357643192F423F44284GXabT72B4B6250655368566D597133743677397A2543164629";
    public static final String JWT_EXPIRED_DURATION = "31536000000";

    public static Date getDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    public static SearchDto getSearchDto() {
        return SearchDto.builder().search("").build();
    }

    public static PageDto getPageDto() {
        return PageDto.builder().search("").page(1).size(10).build();
    }

    public static HeaderRequest getHeaderRequest() {
        return HeaderRequest.builder().userId(1L).userName("Ucup").build();
    }

    public static MPosition getPosition() {
        return MPosition.builder().id(1L).name("Manager").basicSalary(10_000_000D).allowance(500_000D).build();
    }

    public static PositionRequest getPositionRequest() {
        return PositionRequest.builder().name("Manager").basicSalary(10_000_000D).allowance(500_000D).build();
    }

    public static PositionResponse getPositionResponse() {
        return EntityHelper.toPositionResponse(getPosition());
    }

    public static MEmployee getEmployee() {
        return MEmployee.builder()
                .id(1L)
                .fullName("ucup")
                .address("Padang")
                .dateOfBirth(getDate("01-01-1991"))
                .phoneNumber("083180808080")
                .email("ucup@gmail.com")
                .joinDate(getDate("01-09-2024"))
                .position(getPosition())
                .build();
    }

    public static EmployeeRequest getEmployeeRequest() {
        return EmployeeRequest.builder()
                .fullName("ucup")
                .address("Padang")
                .dateOfBirth(getDate("01-01-1991"))
                .phoneNumber("083180808080")
                .email("ucup@gmail.com")
                .joinDate(getDate("01-09-2024"))
                .positionId(1L)
                .build();
    }

    public static EmployeeResponse getEmployeeResponse() {
        return EntityHelper.toEmployeeResponse(getEmployee());
    }

    public static LoginRequest getLoginRequest() {
        return LoginRequest.builder()
                .username("admin")
                .password("admin147")
                .build();
    }

    public static LoginResponse getLoginResponse() {
        return LoginResponse.builder()
                .jwt(JWT)
                .build();
    }

    public static MUser getUser() {
        return MUser.builder()
                .id(1L)
                .username("admin")
                .password("$2a$12$aXJHIHcSPjINQaVjgxmKgOtsN9Ifb7D3TatHZYBjIk4ZEVu6E7lb2")
                .build();
    }

    public static JwtComponentDto getJwtComponentDto() {
        return JwtComponentDto.builder().username("admin").build();
    }
}
