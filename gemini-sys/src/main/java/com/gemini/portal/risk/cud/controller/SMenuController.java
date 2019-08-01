package com.gemini.portal.risk.cud.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SMenuDto;
import com.uepay.corebusiness.risk.cud.service.service.SMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 菜单表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/cud/s/menu")
public class SMenuController {

    @Autowired
    SMenuService sMenuService;

    @GetMapping("/{id}")
    public Mono<SMenuDto> get(@PathVariable final Long id) {
        return Mono.just(sMenuService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SMenuDto>> list(@RequestBody final SMenuDto dto) {
        return Mono.just(sMenuService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SMenuDto dto) {
        sMenuService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SMenuDto dto) {
        sMenuService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sMenuService.delete(id);
        return Mono.just(true);
    }
}