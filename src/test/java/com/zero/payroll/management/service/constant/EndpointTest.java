package com.zero.payroll.management.service.constant;

import com.zero.payroll.management.service.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class EndpointTest {

    @Test
    void testInstanceEndpoint() throws NoSuchMethodException {
        Constructor<Endpoint> constructor = Endpoint.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException e = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(e.getCause() instanceof BusinessException);
        assertEquals(GlobalMessage.INTERNAL_SERVER_ERROR.message, e.getCause().getMessage());
    }
}