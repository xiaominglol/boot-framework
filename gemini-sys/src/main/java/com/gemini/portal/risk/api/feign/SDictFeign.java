package com.gemini.portal.risk.api.feign;

import com.uepay.corebusiness.risk.api.facade.dto.SDictDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典表
 * @author wenge.cai
 */
@FeignClient(contextId = "sDictApiClient", name = "business-risk-api-service")
public interface SDictFeign {

    String root = "/risk/api/s/dict";

    @GetMapping(root + "/{id}")
    SDictDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SDictDto> list(SDictDto sDictDto);

    @PostMapping(root)
    boolean insert(SDictDto sDictDto);

    @PutMapping(root)
    boolean update(SDictDto sDictDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
