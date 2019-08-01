package com.gemini.portal.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.portal.module.sys.service.DictService;
import com.gemini.portal.module.sys.mapper.DictMapper;
import com.gemini.portal.module.sys.model.Dict;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 小明不读书
 * @date 2018-10-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "dictCache")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

}
