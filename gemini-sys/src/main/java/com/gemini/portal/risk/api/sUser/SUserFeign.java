package com.gemini.portal.risk.api.sUser;

import com.uepay.corebusiness.risk.api.facade.dto.SUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表
 * @author wenge.cai
 */
@FeignClient(contextId = "sUserApiClient", name = "business-risk-api-service")
public interface SUserFeign {

    String root = "/risk/api/s/user";

    @GetMapping(root + "/{id}")
    SUserDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SUserDto> list(SUserDto sUserDto);

    @PostMapping(root)
    boolean insert(SUserDto sUserDto);

    @PutMapping(root)
    boolean update(SUserDto sUserDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
