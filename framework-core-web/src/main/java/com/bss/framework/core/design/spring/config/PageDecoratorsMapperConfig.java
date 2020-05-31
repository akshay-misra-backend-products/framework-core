package com.bss.framework.core.design.spring.config;

import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.decorators.PageDecorator;
import com.bss.framework.core.design.decorators.impl.CreatePageDecorator;
import com.bss.framework.core.design.decorators.impl.DetailsPageDecorator;
import com.bss.framework.core.design.decorators.impl.ListPageDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PageDecoratorsMapperConfig {

    @Autowired
    CreatePageDecorator createPageDecorator;

    @Autowired
    DetailsPageDecorator detailsPageDecorator;

    @Autowired
    ListPageDecorator listPageDecorator;

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public Map<Layout, PageDecorator> pageDecoratorsMapper() {
        Map<String, PageDecorator> decorators = appContext.getBeansOfType(PageDecorator.class);
        decorators.forEach((k,v)->{
            System.out.println("****************** PageDecoratorsMapperConfig, decorator: " + k + " - " + v);
        });

        Map<Layout, PageDecorator> config = new HashMap<>();
        config.put(Layout.TABLES, listPageDecorator);
        config.put(Layout.FORM, createPageDecorator);
        config.put(Layout.DETAILS, detailsPageDecorator);


        return config;
    }
}
