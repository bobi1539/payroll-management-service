package com.zero.payroll.management.controller;

import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.helper.ObjectDummy;

public abstract class ControllerTest {

    protected final Long id = 1L;
    protected final HeaderRequest header = ObjectDummy.getHeaderRequest();
}
