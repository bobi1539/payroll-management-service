package com.zero.payroll.management.controller;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.response.BaseResponse;
import com.zero.payroll.management.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ErrorHandlerControllerTest {

    private final ErrorHandlerController controller = new ErrorHandlerController();

    @Test
    void testException() {
        ResponseEntity<BaseResponse<Object>> response = controller.exception(new Exception());
        assertEquals(GlobalMessage.INTERNAL_SERVER_ERROR.status.value(), response.getStatusCode().value());
    }

    @Test
    void testBusinessException() {
        BusinessException e = new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
        ResponseEntity<BaseResponse<Object>> response = controller.exception(e);
        assertEquals(GlobalMessage.INTERNAL_SERVER_ERROR.status.value(), response.getStatusCode().value());
    }

    @Test
    void testMethodArgumentNotValidException_WithFieldErrors() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        FieldError fieldError = new FieldError("objectName", "field", "error message");
        when(exception.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        ResponseEntity<BaseResponse<Object>> response = controller.exception(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error message", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getCode());
    }

    @Test
    void testMethodArgumentNotValidException_WithoutFieldErrors() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getFieldErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<BaseResponse<Object>> response = controller.exception(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getCode());
    }
}