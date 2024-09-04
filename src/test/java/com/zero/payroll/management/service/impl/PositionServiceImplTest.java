package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.request.PositionRequest;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.helper.ObjectDummy;
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
import static org.mockito.Mockito.*;

class PositionServiceImplTest extends ServiceTest {

    @InjectMocks
    private PositionServiceImpl service;

    @Mock
    private PositionRepository repository;

    private final MPosition position = ObjectDummy.getPosition();
    private final PositionRequest request = ObjectDummy.getPositionRequest();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFindAll_Success() {
        when(repository.findAll(any(Specification.class), any(Sort.class))).thenReturn(getPositions());

        List<PositionResponse> responses = service.findAll(searchDto);
        assertEquals(2, responses.size());

        verify(repository, times(1)).findAll(any(Specification.class), any(Sort.class));
    }

    private List<MPosition> getPositions() {
        return List.of(position, position);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFindAllPagination_Success() {
        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(getPositionPage());

        Page<PositionResponse> responses = service.findAllPagination(pageDto);
        assertEquals(2, responses.getTotalElements());

        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    private Page<MPosition> getPositionPage() {
        return new PageImpl<>(getPositions());
    }

    @Test
    void testFindById_Success() {
        when(repository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(position));

        PositionResponse response = service.findById(id);
        assertEquals(position.getId(), response.getId());
        assertEquals(position.getName(), response.getName());

        verify(repository, times(1)).findByIdAndIsDeleted(id, false);
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.empty());

        BusinessException e = assertThrows(BusinessException.class, () -> service.findById(id));
        assertEquals(GlobalMessage.DATA_NOT_FOUND.status, e.getStatus());
        assertEquals(GlobalMessage.DATA_NOT_FOUND.message, e.getMessage());

        verify(repository, times(1)).findByIdAndIsDeleted(id, false);
    }

    @Test
    void testCreate_Success() {
        when(repository.save(any())).thenReturn(position);

        PositionResponse response = service.create(request, header);
        assertEquals(position.getId(), response.getId());
        assertEquals(position.getName(), response.getName());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdate_Success() {
        when(repository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(position));
        when(repository.save(any())).thenReturn(position);

        PositionResponse response = service.update(id, request, header);
        assertEquals(position.getId(), response.getId());
        assertEquals(position.getName(), response.getName());

        verify(repository, times(1)).findByIdAndIsDeleted(id, false);
        verify(repository, times(1)).save(any());
    }

    @Test
    void testDelete_Success() {
        when(repository.findByIdAndIsDeleted(id, false)).thenReturn(Optional.of(position));
        when(repository.save(any())).thenReturn(position);

        PositionResponse response = service.delete(id);
        assertEquals(position.getId(), response.getId());
        assertEquals(position.getName(), response.getName());
        assertTrue(response.isDeleted());

        verify(repository, times(1)).findByIdAndIsDeleted(id, false);
        verify(repository, times(1)).save(any());
    }
}