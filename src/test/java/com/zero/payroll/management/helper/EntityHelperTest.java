package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class EntityHelperTest {

    @Test
    void testInstanceEntityHelper() throws NoSuchMethodException {
        Constructor<EntityHelper> constructor = EntityHelper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException e = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(e.getCause() instanceof BusinessException);
        assertEquals(GlobalMessage.INTERNAL_SERVER_ERROR.message, e.getCause().getMessage());
    }
}