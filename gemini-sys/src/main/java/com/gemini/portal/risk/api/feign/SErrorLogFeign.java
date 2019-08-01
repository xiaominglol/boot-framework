package com.gemini.portal.risk.api.feign;

import com.uepay.corebusiness.risk.api.facade.dto.SErrorLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 错误日志表
 * @author wenge.cai
 */
@FeignClient(contextId = "sErrorLogApiClient", name = "business-risk-api-service")
public interface SErrorLogFeign {

    String root = "/risk/api/s/error/log";

    @GetMapping(root + "/{id}")
    SErrorLogDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SErrorLogDto> list(SErrorLogDto sErrorLogDto);

    @PostMapping(root)
    boolean insert(SErrorLogDto sErrorLogDto);

    @PutMapping(root)
    boolean update(SErrorLogDto sErrorLogDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
