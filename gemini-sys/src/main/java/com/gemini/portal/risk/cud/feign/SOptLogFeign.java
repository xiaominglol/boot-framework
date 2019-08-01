package com.gemini.portal.risk.cud.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志表
 * @author wenge.cai
 */
@FeignClient(contextId = "sOptLogCudClient", name = "business-risk-cud-service")
public interface SOptLogFeign {

    String root = "/risk/cud/s/opt/log";

    @GetMapping(root + "/{id}")
    SOptLogDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SOptLogDto> list(SOptLogDto dto);

    @PostMapping(root)
    boolean insert(SOptLogDto dto);

    @PutMapping(root)
    boolean update(SOptLogDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
