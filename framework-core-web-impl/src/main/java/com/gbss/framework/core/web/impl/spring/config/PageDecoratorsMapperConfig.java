package com.gbss.framework.core.web.impl.spring.config;

import com.gbss.framework.core.web.api.decorators.PageDecorator;
import com.gbss.framework.core.web.impl.decorators.CreatePageDecorator;
import com.gbss.framework.core.web.impl.decorators.DetailsPageDecorator;
import com.gbss.framework.core.web.impl.decorators.ListPageDecorator;
import com.gbss.framework.core.web.model.constants.Layout;
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
