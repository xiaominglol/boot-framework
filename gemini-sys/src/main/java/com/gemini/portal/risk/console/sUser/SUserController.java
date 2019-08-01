package com.gemini.portal.risk.console.sUser;

import com.uepay.corebusiness.risk.api.facade.dto.SUserDto;
import com.uepay.corebusiness.risk.api.facade.feign.SUserFeign;
import com.uepay.corebusiness.risk.base.enums.HttpCodeEnum;
import com.uepay.corebusiness.risk.base.result.ResponseData;
import com.uepay.corebusiness.risk.base.util.BeanUtils;
import com.uepay.corebusiness.risk.console.service.SUserService;
import com.uepay.corebusiness.risk.console.vo.SUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 用户表
 *
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/console/s/user")
public class SUserController {

    @Autowired
    SUserFeign sUserFeign;

    @Autowired
    SUserService sUserService;

    @GetMapping("/{id}")
    Mono<ResponseData> get(@PathVariable final Long id) {
        return Mono
                .justOrEmpty(sUserService.get(id))
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
    Mono<ResponseData> list(final SUserVo vo) {
        return Mono
                .just(sUserService.list(vo))
                .map(list -> {
                    return new ResponseData(list);
                })
                .onErrorResume(e -> {
                    log.error("list: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @GetMapping("/{current}/{size}")
    Mono<ResponseData> page(final SUserVo vo, @PathVariable Long current, @PathVariable Long size) {
        return Mono
                .just(sUserService.page(vo, current, size))
                .map(page -> {
                    return new ResponseData(page);
                })
                .onErrorResume(e -> {
                    log.error("page: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @PostMapping
    Mono<ResponseData> insert(@RequestBody final SUserVo vo) {
        return Mono
                .justOrEmpty(BeanUtils.copy(vo, SUserDto.class))
                .map(dto -> {
                    return sUserFeign.insert(dto);
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
    Mono<ResponseData> update(@RequestBody final SUserVo vo) {
        return Mono
                .justOrEmpty(BeanUtils.copy(vo, SUserDto.class))
                .map(dto -> {
                    return sUserFeign.update(dto);
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
                .justOrEmpty(sUserService.get(id))
                .map(vo -> {
                    return sUserFeign.delete(id);
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