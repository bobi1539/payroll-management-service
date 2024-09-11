package com.zero.payroll.management.service.impl;

import com.zero.payroll.management.constant.GlobalMessage;
import com.zero.payroll.management.dto.PageDto;
import com.zero.payroll.management.dto.SearchDto;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.dto.request.PositionRequest;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.entity.BaseEntity;
import com.zero.payroll.management.entity.MPosition;
import com.zero.payroll.management.exception.BusinessException;
import com.zero.payroll.management.helper.EntityHelper;
import com.zero.payroll.management.helper.SpecificationHelper;
import com.zero.payroll.management.repository.PositionRepository;
import com.zero.payroll.management.service.AbstractCrudService;
import com.zero.payroll.management.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PositionServiceImpl extends AbstractCrudService implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public List<PositionResponse> findAll(SearchDto searchDto) {
        List<MPosition> positions = positionRepository.findAll(createSpec(searchDto), sortByIdAsc());
        return positions.stream().map(EntityHelper::toPositionResponse).toList();
    }

    @Override
    public Page<PositionResponse> findAllPagination(PageDto pageDto) {
        Page<MPosition> positions = positionRepository.findAll(createSpec(pageDto), pageableSortByIdAsc(pageDto));
        return positions.map(EntityHelper::toPositionResponse);
    }

    @Override
    public PositionResponse findById(Long id) {
        return EntityHelper.toPositionResponse(getById(id));
    }

    @Override
    public PositionResponse create(PositionRequest request, HeaderRequest header) {
        MPosition position = MPosition.builder().build();
        setPosition(position, request);
        setCreatedBy(position, header);
        setUpdatedBy(position, header);

        position = positionRepository.save(position);
        return EntityHelper.toPositionResponse(position);
    }

    @Override
    public PositionResponse update(Long id, PositionRequest request, HeaderRequest header) {
        MPosition position = getById(id);
        setPosition(position, request);
        setUpdatedBy(position, header);

        position = positionRepository.save(position);
        return EntityHelper.toPositionResponse(position);
    }

    @Override
    public PositionResponse delete(Long id) {
        MPosition position = getById(id);
        position.setDeleted(true);

        position = positionRepository.save(position);
        return EntityHelper.toPositionResponse(position);
    }

    private MPosition getById(Long id) {
        return positionRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));
    }

    private void setPosition(MPosition position, PositionRequest request) {
        position.setName(request.getName());
        position.setBasicSalary(request.getBasicSalary());
        position.setAllowance(request.getAllowance());
    }

    private Specification<MPosition> createSpec(SearchDto searchDto) {
        Specification<MPosition> spec = SpecificationHelper.stringLike(MPosition.FIELD_NAME, searchDto.getSearch());
        return spec.and(SpecificationHelper.objectEquals(BaseEntity.FIELD_IS_DELETED, false));
    }
}
