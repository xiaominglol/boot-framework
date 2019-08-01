package com.gemini.portal.risk.cud.sLoginLog;

import com.uepay.corebusiness.risk.cud.facade.dto.SLoginLogDto;
import com.uepay.corebusiness.risk.cud.service.service.SLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/cud/s/login/log")
public class SLoginLogController {

    @Autowired
    SLoginLogService sLoginLogService;

    @GetMapping("/{id}")
    public Mono<SLoginLogDto> get(@PathVariable final Long id) {
        return Mono.just(sLoginLogService.get(id));
    }

    @PostMapping("/list")
    public Mono<List<SLoginLogDto>> list(@RequestBody final SLoginLogDto dto) {
        return Mono.just(sLoginLogService.list(dto));
    }

    @PostMapping
    public Mono<Boolean> insert(@RequestBody final SLoginLogDto dto) {
        sLoginLogService.insert(dto);
        return Mono.just(true);
    }

    @PutMapping
    public Mono<Boolean> update(@RequestBody final SLoginLogDto dto) {
        sLoginLogService.update(dto);
        return Mono.just(true);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable final Long id) {
        sLoginLogService.delete(id);
        return Mono.just(true);
    }
}