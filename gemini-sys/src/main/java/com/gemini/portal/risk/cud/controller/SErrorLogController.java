package com.gemini.portal.risk.cud.controller;

import com.uepay.corebusiness.risk.cud.facade.dto.SErrorLogDto;
import com.uepay.corebusiness.risk.cud.service.service.SErrorLogService;
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
@RequestMapping("/risk/cud/s/error/log")
public class SErrorLogController {

    @Autowired
    SErrorLogService sErrorLogService;

    @GetMapping("/{id}")
    public Mono<SErrorLogDto> get(@PathVariable final Long id) {
        return Mono.just(sErrorLogService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SErrorLogDto>> list(@RequestBody final SErrorLogDto dto) {
        return Mono.just(sErrorLogService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SErrorLogDto dto) {
        sErrorLogService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SErrorLogDto dto) {
        sErrorLogService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sErrorLogService.delete(id);
        return Mono.just(true);
    }
}