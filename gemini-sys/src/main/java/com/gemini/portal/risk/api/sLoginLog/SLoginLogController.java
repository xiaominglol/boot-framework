package com.gemini.portal.risk.api.sLoginLog;

import com.uepay.corebusiness.risk.cud.facade.dto.SLoginLogDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SLoginLogFeign;
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
@RequestMapping("/risk/api/s/login/log")
public class SLoginLogController {

    @Autowired
    SLoginLogFeign sLoginLogFeign;

    @GetMapping("/{id}")
    Mono<SLoginLogDto> get(@PathVariable final Long id) {
        return Mono.just(sLoginLogFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SLoginLogDto>> list(@RequestBody final SLoginLogDto dto) {
        return Mono.just(sLoginLogFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SLoginLogDto dto) {
        return Mono.just(sLoginLogFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SLoginLogDto dto) {
        return Mono.just(sLoginLogFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sLoginLogFeign.delete(id));
    }
}