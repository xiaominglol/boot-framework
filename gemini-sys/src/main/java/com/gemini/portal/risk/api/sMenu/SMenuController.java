package com.gemini.portal.risk.api.sMenu;

import com.uepay.corebusiness.risk.cud.facade.dto.SMenuDto;
import com.uepay.corebusiness.risk.cud.facade.feign.SMenuFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 菜单表
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/api/s/menu")
public class SMenuController {

    @Autowired
    SMenuFeign sMenuFeign;

    @GetMapping("/{id}")
    Mono<SMenuDto> get(@PathVariable final Long id) {
        return Mono.just(sMenuFeign.get(id));
    }

    @PostMapping("/list")
    Mono<List<SMenuDto>> list(@RequestBody final SMenuDto dto) {
        return Mono.just(sMenuFeign.list(dto));
    }

    @PostMapping
    Mono<Boolean> insert(@RequestBody final SMenuDto dto) {
        return Mono.just(sMenuFeign.insert(dto));
    }

    @PutMapping
    Mono<Boolean> update(@RequestBody final SMenuDto dto) {
        return Mono.just(sMenuFeign.update(dto));
    }

    @DeleteMapping("/{id}")
    Mono<Boolean> delete(@PathVariable final Long id) {
        return Mono.just(sMenuFeign.delete(id));
    }
}