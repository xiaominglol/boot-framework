package com.gemini.portal.risk.cud.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SOrgDto;
import com.uepay.corebusiness.risk.cud.service.service.SOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 组织架构表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/cud/s/org")
public class SOrgController {

    @Autowired
    SOrgService sOrgService;

    @GetMapping("/{id}")
    public Mono<SOrgDto> get(@PathVariable final Long id) {
        return Mono.just(sOrgService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SOrgDto>> list(@RequestBody final SOrgDto dto) {
        return Mono.just(sOrgService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SOrgDto dto) {
        sOrgService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SOrgDto dto) {
        sOrgService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sOrgService.delete(id);
        return Mono.just(true);
    }
}