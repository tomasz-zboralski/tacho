package com.crud.tacho.controller;

import com.crud.tacho.domain.InfringementDto;
import com.crud.tacho.mapper.InfringementMapper;
import com.crud.tacho.service.InfringementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class InfringementController {

    private final InfringementService infringementService;
    private final InfringementMapper infringementMapper;

    @GetMapping(value = "infringements")
    public List<InfringementDto> getInfringements() {
        return infringementMapper.mapToInfringementDtoList(
                infringementService.getAllValidInfringements()
        );
    }

}
