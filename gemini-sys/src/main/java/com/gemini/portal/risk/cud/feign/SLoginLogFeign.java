package com.gemini.portal.risk.cud.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@FeignClient(contextId = "sLoginLogCudClient", name = "business-risk-cud-service")
public interface SLoginLogFeign {

    String root = "/risk/cud/s/login/log";

    @GetMapping(root + "/{id}")
    SLoginLogDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SLoginLogDto> list(SLoginLogDto dto);

    @PostMapping(root)
    boolean insert(SLoginLogDto dto);

    @PutMapping(root)
    boolean update(SLoginLogDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
