package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.admin.module.sys.mapper.OrgMapper;
import com.gemini.admin.module.sys.model.Org;
import com.gemini.admin.module.sys.service.OrgService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 小明不读书
 * @date 2018-10-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "orgCache")
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {

}
