package com.gemini.portal.risk.api.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SOptLogDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SOptLogFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 操作日志表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/api/s/opt/log")
public class SOptLogController {

    @Autowired
    SOptLogFeign sOptLogFeign;

    @GetMapping("/{id}")
    Mono<SOptLogDto> get(@PathVariable final Long id) {
        return Mono.just(sOptLogFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SOptLogDto>> list(@RequestBody final SOptLogDto dto) {
        return Mono.just(sOptLogFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SOptLogDto dto) {
        return Mono.just(sOptLogFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SOptLogDto dto) {
        return Mono.just(sOptLogFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sOptLogFeign.delete(id));
    }
}