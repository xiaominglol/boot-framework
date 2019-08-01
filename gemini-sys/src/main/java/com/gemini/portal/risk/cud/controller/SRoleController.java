package com.gemini.portal.risk.cud.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SRoleDto;
import com.uepay.corebusiness.risk.cud.service.service.SRoleService;
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
@RequestMapping("/risk/cud/s/role")
public class SRoleController {

    @Autowired
    SRoleService sRoleService;

    @GetMapping("/{id}")
    public Mono<SRoleDto> get(@PathVariable final Long id) {
        return Mono.just(sRoleService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SRoleDto>> list(@RequestBody final SRoleDto dto) {
        return Mono.just(sRoleService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SRoleDto dto) {
        sRoleService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SRoleDto dto) {
        sRoleService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sRoleService.delete(id);
        return Mono.just(true);
    }
}