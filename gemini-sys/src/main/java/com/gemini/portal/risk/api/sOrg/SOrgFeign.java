package com.gemini.portal.risk.api.sOrg;

import com.uepay.corebusiness.risk.api.facade.dto.SOrgDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织架构表
 * @author wenge.cai
 */
@FeignClient(contextId = "sOrgApiClient", name = "business-risk-api-service")
public interface SOrgFeign {

    String root = "/risk/api/s/org";

    @GetMapping(root + "/{id}")
    SOrgDto get(@PathVariable(value = "id") Long id);

    @PostMapping(root + "/list")
    List<SOrgDto> list(SOrgDto sOrgDto);

    @PostMapping(root)
    boolean insert(SOrgDto sOrgDto);

    @PutMapping(root)
    boolean update(SOrgDto sOrgDto);

    @DeleteMapping(root + "/{id}")
    boolean delete(@PathVariable(value = "id") Long id);
}
