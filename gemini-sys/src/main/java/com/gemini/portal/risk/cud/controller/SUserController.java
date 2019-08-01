package com.gemini.portal.risk.cud.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SUserDto;
import com.uepay.corebusiness.risk.cud.service.service.SUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 用户表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/cud/s/user")
public class SUserController {

    @Autowired
    SUserService sUserService;

    @GetMapping("/{id}")
    public Mono<SUserDto> get(@PathVariable final Long id) {
        return Mono.just(sUserService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SUserDto>> list(@RequestBody final SUserDto dto) {
        return Mono.just(sUserService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SUserDto dto) {
        sUserService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SUserDto dto) {
        sUserService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sUserService.delete(id);
        return Mono.just(true);
    }
}