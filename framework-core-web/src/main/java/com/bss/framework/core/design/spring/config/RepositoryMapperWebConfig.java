package com.bss.framework.core.design.spring.config;

import com.bss.framework.core.design.repositories.TabRepository;
import com.bss.framework.core.schema.spring.config.RepositoryMapperConfig;
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
        config.put("5eaaa5862e2efbf64a9f4a5b", tabRepository);
        return config;
    }
}
