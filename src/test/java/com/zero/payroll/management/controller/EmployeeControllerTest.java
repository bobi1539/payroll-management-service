package com.zero.payroll.management.controller;

import com.zero.payroll.management.dto.request.EmployeeRequest;
import com.zero.payroll.management.dto.response.EmployeeResponse;
import com.zero.payroll.management.helper.ObjectDummy;
import com.zero.payroll.management.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeControllerTest extends ControllerTest {

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    private final EmployeeRequest employeeRequest = ObjectDummy.getEmployeeRequest();
    private final EmployeeResponse employeeResponse = ObjectDummy.getEmployeeResponse();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(service.findAll(any())).thenReturn(getEmployeeResponses());

        List<EmployeeResponse> responses = controller.findAll("");
        assertEquals(2, responses.size());

        verify(service, times(1)).findAll(any());
    }

    private List<EmployeeResponse> getEmployeeResponses() {
        return List.of(employeeResponse, employeeResponse);
    }

    @Test
    void testFindAllPagination() {
        when(service.findAllPagination(any())).thenReturn(getEmployeeResponsePage());

        Page<EmployeeResponse> responses = controller.findAllPagination("", 1, 10);
        assertEquals(2, responses.getTotalElements());

        verify(service, times(1)).findAllPagination(any());
    }

    private Page<EmployeeResponse> getEmployeeResponsePage() {
        return new PageImpl<>(getEmployeeResponses());
    }

    @Test
    void testFindById() {
        when(service.findById(id)).thenReturn(employeeResponse);

        EmployeeResponse response = controller.findById(id);
        assertEquals(employeeResponse.getId(), response.getId());
        assertEquals(employeeResponse.getFullName(), response.getFullName());

        verify(service, times(1)).findById(id);
    }

    @Test
    void testCreate() {
        when(service.create(any(), any())).thenReturn(employeeResponse);

        EmployeeResponse response = controller.create(employeeRequest, header);
        assertEquals(employeeResponse.getId(), response.getId());
        assertEquals(employeeResponse.getFullName(), response.getFullName());

        verify(service, times(1)).create(any(), any());
    }

    @Test
    void testUpdate() {
        when(service.update(any(), any(), any())).thenReturn(employeeResponse);

        EmployeeResponse response = controller.update(id, employeeRequest, header);
        assertEquals(employeeResponse.getId(), response.getId());
        assertEquals(employeeResponse.getFullName(), response.getFullName());

        verify(service, times(1)).update(any(), any(), any());
    }

    @Test
    void testDelete() {
        when(service.delete(any())).thenReturn(employeeResponse);

        EmployeeResponse response = controller.delete(id);
        assertEquals(employeeResponse.getId(), response.getId());
        assertEquals(employeeResponse.getFullName(), response.getFullName());

        verify(service, times(1)).delete(any());
    }

}