package com.gemini.portal.risk.cud.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表
 * @author wenge.cai
 */
@FeignClient(contextId = "sUserCudClient", name = "business-risk-cud-service")
public interface SUserFeign {

    String root = "/risk/cud/s/user";

    @GetMapping(root + "/{id}")
    SUserDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SUserDto> list(SUserDto dto);

    @PostMapping(root)
    boolean insert(SUserDto dto);

    @PutMapping(root)
    boolean update(SUserDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
