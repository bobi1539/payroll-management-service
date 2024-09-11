package com.zero.payroll.management.controller;

import com.zero.payroll.management.constant.Endpoint;
import com.zero.payroll.management.dto.request.HeaderRequest;
import com.zero.payroll.management.dto.request.PositionRequest;
import com.zero.payroll.management.dto.response.PositionResponse;
import com.zero.payroll.management.service.PositionService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.POSITION)
@AllArgsConstructor
public class PositionController extends BaseController {

    private final PositionService positionService;

    @GetMapping("/all")
    public List<PositionResponse> findAll(@RequestParam(required = false) String search) {
        return positionService.findAll(buildSearchDto(search));
    }

    @GetMapping
    public Page<PositionResponse> findAllPagination(
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return positionService.findAllPagination(buildPageDto(search, page, size));
    }

    @GetMapping("/{id}")
    public PositionResponse findById(@PathVariable Long id) {
        return positionService.findById(id);
    }

    @PostMapping
    public PositionResponse create(
            @RequestBody @Valid PositionRequest request,
            @Parameter(hidden = true) @ModelAttribute(name = "header") HeaderRequest header
    ) {
        return positionService.create(request, header);
    }

    @PutMapping("/{id}")
    public PositionResponse update(
            @PathVariable Long id,
            @RequestBody @Valid PositionRequest request,
            @Parameter(hidden = true) @ModelAttribute(name = "header") HeaderRequest header
    ) {
        return positionService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public PositionResponse delete(@PathVariable Long id) {
        return positionService.delete(id);
    }
}
