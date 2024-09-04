package com.zero.payroll.management.controller;

import com.zero.payroll.management.dto.request.PositionRequest;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.helper.ObjectDummy;
import com.zero.payroll.management.service.impl.PositionServiceImpl;
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

class PositionControllerTest extends ControllerTest {

    @InjectMocks
    private PositionController controller;

    @Mock
    private PositionServiceImpl service;

    private final PositionRequest positionRequest = ObjectDummy.getPositionRequest();
    private final PositionResponse positionResponse = ObjectDummy.getPositionResponse();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(service.findAll(any())).thenReturn(getPositionResponses());

        List<PositionResponse> responses = controller.findAll("");
        assertEquals(2, responses.size());

        verify(service, times(1)).findAll(any());
    }

    private List<PositionResponse> getPositionResponses() {
        return List.of(positionResponse, positionResponse);
    }

    @Test
    void testFindAllPagination() {
        when(service.findAllPagination(any())).thenReturn(getPositionResponsePage());

        Page<PositionResponse> responses = controller.findAllPagination("", 1, 10);
        assertEquals(2, responses.getTotalElements());

        verify(service, times(1)).findAllPagination(any());
    }

    private Page<PositionResponse> getPositionResponsePage() {
        return new PageImpl<>(getPositionResponses());
    }

    @Test
    void testFindById() {
        when(service.findById(id)).thenReturn(positionResponse);

        PositionResponse response = controller.findById(id);
        assertEquals(positionResponse.getId(), response.getId());
        assertEquals(positionResponse.getName(), response.getName());

        verify(service, times(1)).findById(id);
    }

    @Test
    void testCreate() {
        when(service.create(any(), any())).thenReturn(positionResponse);

        PositionResponse response = controller.create(positionRequest, header);
        assertEquals(positionResponse.getId(), response.getId());
        assertEquals(positionResponse.getName(), response.getName());

        verify(service, times(1)).create(any(), any());
    }

    @Test
    void testUpdate() {
        when(service.update(any(), any(), any())).thenReturn(positionResponse);

        PositionResponse response = controller.update(id, positionRequest, header);
        assertEquals(positionResponse.getId(), response.getId());
        assertEquals(positionResponse.getName(), response.getName());

        verify(service, times(1)).update(any(), any(), any());
    }

    @Test
    void testDelete() {
        when(service.delete(any())).thenReturn(positionResponse);

        PositionResponse response = controller.delete(id);
        assertEquals(positionResponse.getId(), response.getId());
        assertEquals(positionResponse.getName(), response.getName());

        verify(service, times(1)).delete(any());
    }
}