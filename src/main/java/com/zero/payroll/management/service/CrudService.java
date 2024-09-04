package com.zero.payroll.management.service;

import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.request.HeaderRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudService<T, R> {

    List<R> findAll(SearchDto searchDto);

    Page<R> findAllPagination(PageDto pageDto);

    R findById(Long id);

    R create(T request, HeaderRequest header);

    R update(Long id, T request, HeaderRequest header);

    R delete(Long id);
}
