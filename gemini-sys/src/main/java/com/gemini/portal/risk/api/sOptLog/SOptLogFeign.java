package com.gemini.portal.risk.api.sOptLog;

import com.uepay.corebusiness.risk.api.facade.dto.SOptLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志表
 * @author wenge.cai
 */
@FeignClient(contextId = "sOptLogApiClient", name = "business-risk-api-service")
public interface SOptLogFeign {

    String root = "/risk/api/s/opt/log";

    @GetMapping(root + "/{id}")
    SOptLogDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SOptLogDto> list(SOptLogDto sOptLogDto);

    @PostMapping(root)
    boolean insert(SOptLogDto sOptLogDto);

    @PutMapping(root)
    boolean update(SOptLogDto sOptLogDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
