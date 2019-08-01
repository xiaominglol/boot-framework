package com.gemini.portal.risk.cud.sOrg;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织架构表
 * @author wenge.cai
 */
@FeignClient(contextId = "sOrgCudClient", name = "business-risk-cud-service")
public interface SOrgFeign {

    String root = "/risk/cud/s/org";

    @GetMapping(root + "/{id}")
    SOrgDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SOrgDto> list(SOrgDto dto);

    @PostMapping(root)
    boolean insert(SOrgDto dto);

    @PutMapping(root)
    boolean update(SOrgDto dto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
