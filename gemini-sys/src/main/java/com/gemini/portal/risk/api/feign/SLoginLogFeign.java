package com.gemini.portal.risk.api.feign;

import com.uepay.corebusiness.risk.api.facade.dto.SLoginLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@FeignClient(contextId = "sLoginLogApiClient", name = "business-risk-api-service")
public interface SLoginLogFeign {

    String root = "/risk/api/s/login/log";

    @GetMapping(root + "/{id}")
    SLoginLogDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SLoginLogDto> list(SLoginLogDto sLoginLogDto);

    @PostMapping(root)
    boolean insert(SLoginLogDto sLoginLogDto);

    @PutMapping(root)
    boolean update(SLoginLogDto sLoginLogDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
