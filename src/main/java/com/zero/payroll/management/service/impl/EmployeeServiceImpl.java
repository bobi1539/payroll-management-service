package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.request.EmployeeRequest;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.dto.response.EmployeeResponse;
import com.zero.payroll.management.entity.BaseEntity;
import com.zero.payroll.management.entity.MEmployee;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.helper.EntityHelper;
import com.zero.payroll.management.helper.SpecificationHelper;
import com.zero.payroll.management.repository.EmployeeRepository;
import com.zero.payroll.management.repository.PositionRepository;
import com.zero.payroll.management.service.AbstractCrudService;
import com.zero.payroll.management.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl extends AbstractCrudService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Override
    public List<EmployeeResponse> findAll(SearchDto searchDto) {
        List<MEmployee> employees = employeeRepository.findAll(createSpec(searchDto), sortByIdAsc());
        return employees.stream().map(EntityHelper::toEmployeeResponse).toList();
    }

    @Override
    public Page<EmployeeResponse> findAllPagination(PageDto pageDto) {
        Page<MEmployee> employees = employeeRepository.findAll(createSpec(pageDto), pageableSortByIdAsc(pageDto));
        return employees.map(EntityHelper::toEmployeeResponse);
    }

    @Override
    public EmployeeResponse findById(Long id) {
        return EntityHelper.toEmployeeResponse(getEmployeeById(id));
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request, HeaderRequest header) {
        MEmployee employee = MEmployee.builder().build();
        setEmployee(employee, request);
        setCreatedBy(employee, header);
        setUpdatedBy(employee, header);

        employee = employeeRepository.save(employee);
        return EntityHelper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request, HeaderRequest header) {
        MEmployee employee = getEmployeeById(id);
        setEmployee(employee, request);
        setUpdatedBy(employee, header);

        employee = employeeRepository.save(employee);
        return EntityHelper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse delete(Long id) {
        MEmployee employee = getEmployeeById(id);
        employee.setDeleted(true);

        employee = employeeRepository.save(employee);
        return EntityHelper.toEmployeeResponse(employee);
    }

    private Specification<MEmployee> createSpec(SearchDto searchDto) {
        Specification<MEmployee> spec = SpecificationHelper.stringLike(MEmployee.FIELD_FULL_NAME, searchDto.getSearch());
        return spec.and(SpecificationHelper.objectEquals(BaseEntity.FIELD_IS_DELETED, false));
    }

    private MEmployee getEmployeeById(Long id) {
        return employeeRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));
    }

    private MPosition getPositionById(Long id) {
        return positionRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));
    }

    private void setEmployee(MEmployee employee, EmployeeRequest request) {
        employee.setFullName(request.getFullName());
        employee.setAddress(request.getAddress());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setEmail(request.getEmail());
        employee.setJoinDate(request.getJoinDate());
        employee.setPosition(getPositionById(request.getPositionId()));
    }
}
