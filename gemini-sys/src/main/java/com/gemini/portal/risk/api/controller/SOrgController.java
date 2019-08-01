package com.gemini.portal.risk.api.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SOrgDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SOrgFeign;
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
@RequestMapping("/risk/api/s/org")
public class SOrgController {

    @Autowired
    SOrgFeign sOrgFeign;

    @GetMapping("/{id}")
    Mono<SOrgDto> get(@PathVariable final Long id) {
        return Mono.just(sOrgFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SOrgDto>> list(@RequestBody final SOrgDto dto) {
        return Mono.just(sOrgFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SOrgDto dto) {
        return Mono.just(sOrgFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SOrgDto dto) {
        return Mono.just(sOrgFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sOrgFeign.delete(id));
    }
}