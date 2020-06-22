package com.gbss.framework.core.web.impl.factory;

import com.gbss.framework.core.web.api.decorators.PageDecorator;
import com.gbss.framework.core.web.model.constants.Layout;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class PageLayoutDecoratorsFactory {

    @Resource
    private Map<Layout, PageDecorator> pageDecoratorsMapper;

    public PageDecorator getPageDecoratorByLayout(Layout layout) {
        return pageDecoratorsMapper.get(layout);
    }
}
