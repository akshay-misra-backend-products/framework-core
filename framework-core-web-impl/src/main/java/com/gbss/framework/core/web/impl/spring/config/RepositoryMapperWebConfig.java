package com.gbss.framework.core.web.impl.spring.config;

import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.web.impl.repositories.TabRepository;
import com.gbss.framework.core.impl.spring.config.RepositoryMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

@Configuration
public class RepositoryMapperWebConfig extends RepositoryMapperConfig {

    @Autowired
    TabRepository tabRepository;

    @Bean
    public Map<String, MongoRepository> objectTypeRepositoriesMapper() {
        Map<String, MongoRepository> config = super.objectTypeRepositoriesMapper();
        //config.put(SystemConstants.ObjectTypes.NAVIGATION_TAB, tabRepository);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  config: " + config);
        return config;
    }
}
