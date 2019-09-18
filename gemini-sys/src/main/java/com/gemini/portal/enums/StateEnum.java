package com.gemini.portal.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.service.DictService;

public enum StateEnum implements DictService {
    Enable() {
        @Override
        public Dict dict() {
            return Dict.get(391933416176131L);
        }
    },
    Disable() {
        @Override
        public Dict dict() {
            return Dict.get(391933416176129L);
        }
    }
}
