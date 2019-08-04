package com.gemini.portal.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.service.DictService;

public enum StateEnum implements DictService {
    Enable() {
        @Override
        public Dict dict() {
            Dict dict = new Dict();
            dict.setId(391933416176131L);
            dict.setCode("Enable");
            dict.setName("启用");
            return dict;
        }
    },
    Disable() {
        @Override
        public Dict dict() {
            Dict dict = new Dict();
            dict.setId(391933416176129L);
            dict.setCode("Disable");
            dict.setName("禁用");
            return dict;
        }
    }
}
