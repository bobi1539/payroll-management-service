package com.zero.payroll.management.controller;

import com.zero.payroll.management.constant.Constant;
import com.zero.payroll.management.constant.Endpoint;
import com.zero.payroll.management.dto.request.EmployeeRequest;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.dto.response.EmployeeResponse;
import com.zero.payroll.management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.EMPLOYEE)
@AllArgsConstructor
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class EmployeeController extends BaseController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeResponse> findAll(@RequestParam(required = false) String search) {
        return employeeService.findAll(buildSearchDto(search));
    }

    @GetMapping
    public Page<EmployeeResponse> findAllPagination(
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return employeeService.findAllPagination(buildPageDto(search, page, size));
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public EmployeeResponse create(
            @RequestBody @Valid EmployeeRequest request,
            @Parameter(hidden = true) @ModelAttribute(name = "header") HeaderRequest header
    ) {
        return employeeService.create(request, header);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeRequest request,
            @Parameter(hidden = true) @ModelAttribute(name = "header") HeaderRequest header
    ) {
        return employeeService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public EmployeeResponse delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }
}
