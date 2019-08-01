package com.gemini.portal.risk.api.feign;

import com.uepay.corebusiness.risk.api.facade.dto.SRoleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色表
 * @author wenge.cai
 */
@FeignClient(contextId = "sRoleApiClient", name = "business-risk-api-service")
public interface SRoleFeign {

    String root = "/risk/api/s/role";

    @GetMapping(root + "/{id}")
    SRoleDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SRoleDto> list(SRoleDto sRoleDto);

    @PostMapping(root)
    boolean insert(SRoleDto sRoleDto);

    @PutMapping(root)
    boolean update(SRoleDto sRoleDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
