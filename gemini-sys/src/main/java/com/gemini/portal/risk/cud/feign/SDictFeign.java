package com.gemini.portal.risk.cud.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典表
 * @author wenge.cai
 */
@FeignClient(contextId = "sDictCudClient", name = "business-risk-cud-service")
public interface SDictFeign {

    String root = "/risk/cud/s/dict";

    @GetMapping(root + "/{id}")
    SDictDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SDictDto> list(SDictDto dto);

    @PostMapping(root)
    boolean insert(SDictDto dto);

    @PutMapping(root)
    boolean update(SDictDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
