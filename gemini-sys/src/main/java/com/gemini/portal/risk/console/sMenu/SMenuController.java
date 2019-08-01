package com.gemini.portal.risk.console.sMenu;

import com.uepay.corebusiness.risk.api.facade.dto.SMenuDto;
import com.uepay.corebusiness.risk.api.facade.feign.SMenuFeign;
import com.uepay.corebusiness.risk.base.enums.HttpCodeEnum;
import com.uepay.corebusiness.risk.base.result.ResponseData;
import com.uepay.corebusiness.risk.base.util.BeanUtils;
import com.uepay.corebusiness.risk.console.service.SMenuService;
import com.uepay.corebusiness.risk.console.vo.SMenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 菜单表
 *
 * @author wenge.cai
 */
@Slf4j
@RestController
@RequestMapping("/risk/console/s/menu")
public class SMenuController {

    @Autowired
    SMenuFeign sMenuFeign;

    @Autowired
    SMenuService sMenuService;

    @GetMapping("/{id}")
    Mono<ResponseData> get(@PathVariable final Long id) {
        return Mono
                .justOrEmpty(sMenuService.get(id))
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
    Mono<ResponseData> list(final SMenuVo vo) {
        return Mono
                .just(sMenuService.list(vo))
                .map(list -> {
                    return new ResponseData(list);
                })
                .onErrorResume(e -> {
                    log.error("list: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @GetMapping("/{current}/{size}")
    Mono<ResponseData> page(final SMenuVo vo, @PathVariable Long current, @PathVariable Long size) {
        return Mono
                .just(sMenuService.page(vo, current, size))
                .map(page -> {
                    return new ResponseData(page);
                })
                .onErrorResume(e -> {
                    log.error("page: {}", e);
                    return Mono.just(new ResponseData(HttpCodeEnum.N500, e.getMessage()));
                });
    }

    @PostMapping
    Mono<ResponseData> insert(@RequestBody final SMenuVo vo) {
        return Mono
                .justOrEmpty(BeanUtils.copy(vo, SMenuDto.class))
                .map(dto -> {
                    return sMenuFeign.insert(dto);
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
    Mono<ResponseData> update(@RequestBody final SMenuVo vo) {
        return Mono
                .justOrEmpty(BeanUtils.copy(vo, SMenuDto.class))
                .map(dto -> {
                    return sMenuFeign.update(dto);
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
                .justOrEmpty(sMenuService.get(id))
                .map(vo -> {
                    return sMenuFeign.delete(id);
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