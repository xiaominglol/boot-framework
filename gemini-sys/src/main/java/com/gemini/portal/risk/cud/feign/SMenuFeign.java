package com.gemini.portal.risk.cud.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单表
 * @author wenge.cai
 */
@FeignClient(contextId = "sMenuCudClient", name = "business-risk-cud-service")
public interface SMenuFeign {

    String root = "/risk/cud/s/menu";

    @GetMapping(root + "/{id}")
    SMenuDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SMenuDto> list(SMenuDto dto);

    @PostMapping(root)
    boolean insert(SMenuDto dto);

    @PutMapping(root)
    boolean update(SMenuDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
