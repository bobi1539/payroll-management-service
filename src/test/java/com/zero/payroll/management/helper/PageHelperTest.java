package com.zero.payroll.management.helper;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class PageHelperTest {

    @Test
    void testInstancePageHelper() throws NoSuchMethodException {
        Constructor<PageHelper> constructor = PageHelper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException e = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(e.getCause() instanceof BusinessException);
        assertEquals(GlobalMessage.INTERNAL_SERVER_ERROR.message, e.getCause().getMessage());
    }

    @Test
    void testSortByColumnDesc() {
        Sort sort = PageHelper.sortByColumnDesc("id");
        assertNotNull(sort);
    }

    @Test
    void testGetPageNumber_Zero() {
        int pageNumber = PageHelper.getPageNumber(0);
        assertEquals(0, pageNumber);
    }
}