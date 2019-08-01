package com.gemini.portal.risk.api.sUser;

import com.uepay.corebusiness.risk.cud.facade.dto.SUserDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SUserFeign;
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
@RequestMapping("/risk/api/s/user")
public class SUserController {

    @Autowired
    SUserFeign sUserFeign;

    @GetMapping("/{id}")
    Mono<SUserDto> get(@PathVariable final Long id) {
        return Mono.just(sUserFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SUserDto>> list(@RequestBody final SUserDto dto) {
        return Mono.just(sUserFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SUserDto dto) {
        return Mono.just(sUserFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SUserDto dto) {
        return Mono.just(sUserFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sUserFeign.delete(id));
    }
}