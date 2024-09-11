package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.request.EmployeeRequest;
import com.zero.payroll.management.dto.response.EmployeeResponse;
import com.zero.payroll.management.entity.MEmployee;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.helper.ObjectDummy;
import com.zero.payroll.management.repository.EmployeeRepository;
import com.zero.payroll.management.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest extends ServiceTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PositionRepository positionRepository;

    private final MEmployee employee = ObjectDummy.getEmployee();
    private final MPosition position = ObjectDummy.getPosition();
    private final EmployeeRequest request = ObjectDummy.getEmployeeRequest();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFindAll_Success() {
        when(employeeRepository.findAll(any(Specification.class), any(Sort.class))).thenReturn(getEmployees());

        List<EmployeeResponse> responses = service.findAll(searchDto);
        assertEquals(2, responses.size());

        verify(employeeRepository, times(1)).findAll(any(Specification.class), any(Sort.class));
    }

    private List<MEmployee> getEmployees() {
        return List.of(employee, employee);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFindAllPagination_Success() {
        when(employeeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(getPositionPage());

        Page<EmployeeResponse> responses = service.findAllPagination(pageDto);
        assertEquals(2, responses.getTotalElements());

        verify(employeeRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    private Page<MEmployee> getPositionPage() {
        return new PageImpl<>(getEmployees());
    }

    @Test
    void testFindById_Success() {
        when(employeeRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(employee));

        EmployeeResponse response = service.findById(id);
        assertEquals(employee.getId(), response.getId());
        assertEquals(employee.getFullName(), response.getFullName());

        verify(employeeRepository, times(1)).findByIdAndIsDeleted(id, false);
    }

    @Test
    void testFindById_NotFound() {
        when(employeeRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.empty());

        BusinessException e = assertThrows(BusinessException.class, () -> service.findById(id));
        assertEquals(GlobalMessage.DATA_NOT_FOUND.status, e.getStatus());
        assertEquals(GlobalMessage.DATA_NOT_FOUND.message, e.getMessage());

        verify(employeeRepository, times(1)).findByIdAndIsDeleted(id, false);
    }

    @Test
    void testCreate_Success() {
        when(positionRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(position));
        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeResponse response = service.create(request, header);
        assertEquals(employee.getId(), response.getId());
        assertEquals(employee.getFullName(), response.getFullName());

        verify(positionRepository, times(1)).findByIdAndIsDeleted(id, false);
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    void testCreate_PositionNotFound() {
        when(positionRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.empty());

        BusinessException e = assertThrows(BusinessException.class, () -> service.create(request, header));
        assertEquals(GlobalMessage.DATA_NOT_FOUND.status, e.getStatus());
        assertEquals(GlobalMessage.DATA_NOT_FOUND.message, e.getMessage());

        verify(positionRepository, times(1)).findByIdAndIsDeleted(id, false);
    }

    @Test
    void testUpdate_Success() {
        when(positionRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(position));
        when(employeeRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeResponse response = service.update(id, request, header);
        assertEquals(employee.getId(), response.getId());
        assertEquals(employee.getFullName(), response.getFullName());

        verify(positionRepository, times(1)).findByIdAndIsDeleted(id, false);
        verify(employeeRepository, times(1)).findByIdAndIsDeleted(id, false);
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    void testDelete_Success() {
        when(employeeRepository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeResponse response = service.delete(id);
        assertEquals(employee.getId(), response.getId());
        assertEquals(employee.getFullName(), response.getFullName());
        assertTrue(response.isDeleted());

        verify(employeeRepository, times(1)).findByIdAndIsDeleted(id, false);
        verify(employeeRepository, times(1)).save(any());
    }

}