package com.bss.framework.core.design.factory;

import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.decorators.PageDecorator;
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
