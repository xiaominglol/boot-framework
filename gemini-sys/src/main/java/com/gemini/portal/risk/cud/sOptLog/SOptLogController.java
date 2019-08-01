package com.gemini.portal.risk.cud.sOptLog;

import com.uepay.corebusiness.risk.cud.facade.dto.SOptLogDto;
import com.uepay.corebusiness.risk.cud.service.service.SOptLogService;
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
@RequestMapping("/risk/cud/s/opt/log")
public class SOptLogController {

    @Autowired
    SOptLogService sOptLogService;

    @GetMapping("/{id}")
    public Mono<SOptLogDto> get(@PathVariable final Long id) {
        return Mono.just(sOptLogService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SOptLogDto>> list(@RequestBody final SOptLogDto dto) {
        return Mono.just(sOptLogService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SOptLogDto dto) {
        sOptLogService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SOptLogDto dto) {
        sOptLogService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sOptLogService.delete(id);
        return Mono.just(true);
    }
}