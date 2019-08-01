package com.gemini.portal.risk.api.feign;

import com.uepay.corebusiness.risk.api.facade.dto.SMenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单表
 * @author wenge.cai
 */
@FeignClient(contextId = "sMenuApiClient", name = "business-risk-api-service")
public interface SMenuFeign {

    String root = "/risk/api/s/menu";

    @GetMapping(root + "/{id}")
    SMenuDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SMenuDto> list(SMenuDto sMenuDto);

    @PostMapping(root)
    boolean insert(SMenuDto sMenuDto);

    @PutMapping(root)
    boolean update(SMenuDto sMenuDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
