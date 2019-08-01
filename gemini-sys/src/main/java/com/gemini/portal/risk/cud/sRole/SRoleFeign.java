package com.gemini.portal.risk.cud.sRole;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色表
 * @author wenge.cai
 */
@FeignClient(contextId = "sRoleCudClient", name = "business-risk-cud-service")
public interface SRoleFeign {

    String root = "/risk/cud/s/role";

    @GetMapping(root + "/{id}")
    SRoleDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SRoleDto> list(SRoleDto dto);

    @PostMapping(root)
    boolean insert(SRoleDto dto);

    @PutMapping(root)
    boolean update(SRoleDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
