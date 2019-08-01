package com.gemini.portal.risk.console.controller;

import com.uepay.corebusiness.risk.api.facade.dto.SLoginLogDto;
import com.uepay.corebusiness.risk.api.facade.feign.SLoginLogFeign;
import com.uepay.corebusiness.risk.base.enums.HttpCodeEnum;
import com.uepay.corebusiness.risk.base.result.ResponseData;
import com.uepay.corebusiness.risk.base.util.BeanUtils;
import com.uepay.corebusiness.risk.console.service.SLoginLogService;
import com.uepay.corebusiness.risk.console.vo.SLoginLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 登陆日志表
 *
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/console/s/login/log")
public class SLoginLogController {

    @Autowired
    SLoginLogFeign sLoginLogFeign;

    @Autowired
    SLoginLogService sLoginLogService;

    @GetMapping("/{id}")
    Mono<ResponseData> get(@PathVariable final Long id) {
        return Mono
                .justOrEmpty(sLoginLogService.get(id))
                .map(vo -> {
                    return new ResponseData(vo);
                })
                .switchIfEmpty(Mono.just(new ResponseData(HttpCodeEnum.N410)))
                .onErrorResume(e -> {
                    log.error("get: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @GetMapping
    Mono<ResponseData> list(final SLoginLogVo vo) {
        return Mono
                .just(sLoginLogService.list(vo))
                .map(list -> {
                    return new ResponseData(list);
                })
                .onErrorResume(e -> {
                    log.error("list: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @GetMapping("/{current}/{size}")
    Mono<ResponseData> page(final SLoginLogVo vo, @PathVariable Long current, @PathVariable Long size) {
        return Mono
                .just(sLoginLogService.page(vo, current, size))
                .map(page -> {
                    return new ResponseData(page);
                })
                .onErrorResume(e -> {
                    log.error("page: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @PostMapping
    Mono<ResponseData> insert(@RequestBody final SLoginLogVo vo) {
        return Mono
                .justOrEmpty(BeanUtils.copy(vo, SLoginLogDto.class))
                .map(dto -> {
                    return sLoginLogFeign.insert(dto);
                })
                .map(flag -> {
                    return  new ResponseData(flag ? HttpCodeEnum.N201 : HttpCodeEnum.N400);
                })
                .switchIfEmpty(Mono.just(new ResponseData(HttpCodeEnum.N410)))
                .onErrorResume(e -> {
                    log.error("insert: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @PutMapping
    Mono<ResponseData> update(@RequestBody final SLoginLogVo vo) {
        return Mono
                .justOrEmpty(BeanUtils.copy(vo, SLoginLogDto.class))
                .map(dto -> {
                    return sLoginLogFeign.update(dto);
                })
                .map(flag -> {
                    return  new ResponseData(flag ? HttpCodeEnum.N201 : HttpCodeEnum.N400);
                })
                .switchIfEmpty(Mono.just(new ResponseData(HttpCodeEnum.N410)))
                .onErrorResume(e -> {
                    log.error("update: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @DeleteMapping("/{id}")
    Mono<ResponseData> delete(@PathVariable final Long id) {
        return Mono
                .justOrEmpty(sLoginLogService.get(id))
                .map(vo -> {
                    return sLoginLogFeign.delete(id);
                })
                .map(flag -> {
                    return  new ResponseData(flag ? HttpCodeEnum.N204 : HttpCodeEnum.N400);
                })
                .switchIfEmpty(Mono.just(new ResponseData(HttpCodeEnum.N410)))
                .onErrorResume(e -> {
                    log.error("delete: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }
}