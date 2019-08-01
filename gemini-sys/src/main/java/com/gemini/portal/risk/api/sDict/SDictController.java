package com.gemini.portal.risk.api.sDict;

import com.uepay.corebusiness.risk.cud.facade.dto.SDictDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SDictFeign;
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
@RequestMapping("/risk/api/s/dict")
public class SDictController {

    @Autowired
    SDictFeign sDictFeign;

    @GetMapping("/{id}")
    Mono<SDictDto> get(@PathVariable final Long id) {
        return Mono.just(sDictFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SDictDto>> list(@RequestBody final SDictDto dto) {
        return Mono.just(sDictFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SDictDto dto) {
        return Mono.just(sDictFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SDictDto dto) {
        return Mono.just(sDictFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sDictFeign.delete(id));
    }
}