package com.gemini.portal.risk.api.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SRoleDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SRoleFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 角色表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/api/s/role")
public class SRoleController {

    @Autowired
    SRoleFeign sRoleFeign;

    @GetMapping("/{id}")
    Mono<SRoleDto> get(@PathVariable final Long id) {
        return Mono.just(sRoleFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SRoleDto>> list(@RequestBody final SRoleDto dto) {
        return Mono.just(sRoleFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SRoleDto dto) {
        return Mono.just(sRoleFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SRoleDto dto) {
        return Mono.just(sRoleFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sRoleFeign.delete(id));
    }
}