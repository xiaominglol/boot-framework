package com.gemini.portal.risk.cud.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SDictDto;
import com.uepay.corebusiness.risk.cud.service.service.SDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 字典表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/cud/s/dict")
public class SDictController {

    @Autowired
    SDictService sDictService;

    @GetMapping("/{id}")
    public Mono<SDictDto> get(@PathVariable final Long id) {
        return Mono.just(sDictService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SDictDto>> list(@RequestBody final SDictDto dto) {
        return Mono.just(sDictService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SDictDto dto) {
        sDictService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SDictDto dto) {
        sDictService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sDictService.delete(id);
        return Mono.just(true);
    }
}