package com.gemini.portal.risk.cud.sErrorLog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 错误日志表
 * @author wenge.cai
 */
@FeignClient(contextId = "sErrorLogCudClient", name = "business-risk-cud-service")
public interface SErrorLogFeign {

    String root = "/risk/cud/s/error/log";

    @GetMapping(root + "/{id}")
    SErrorLogDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SErrorLogDto> list(SErrorLogDto dto);

    @PostMapping(root)
    boolean insert(SErrorLogDto dto);

    @PutMapping(root)
    boolean update(SErrorLogDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
