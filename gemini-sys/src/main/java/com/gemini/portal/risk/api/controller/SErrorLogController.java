package com.gemini.portal.risk.api.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SErrorLogDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SErrorLogFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 错误日志表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/api/s/error/log")
public class SErrorLogController {

    @Autowired
    SErrorLogFeign sErrorLogFeign;

    @GetMapping("/{id}")
    Mono<SErrorLogDto> get(@PathVariable final Long id) {
        return Mono.just(sErrorLogFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SErrorLogDto>> list(@RequestBody final SErrorLogDto dto) {
        return Mono.just(sErrorLogFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SErrorLogDto dto) {
        return Mono.just(sErrorLogFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SErrorLogDto dto) {
        return Mono.just(sErrorLogFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sErrorLogFeign.delete(id));
    }
}