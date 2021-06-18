package com.gbss;

import com.gbss.framework.core.impl.service.impl.AttributeSchemaServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableDiscoveryClient
public class Application {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		/*AttributeSchemaServiceImpl schema =  context.getBean(AttributeSchemaServiceImpl.class);
		schema.updateAllAttribute("parentId", "603aad6fe1d7a507c3ba595c");
		schema.updateAllAttributeGroup("parentId", "603aad6fe1d7a507c3ba595c");
		schema.updateAllObjectType();*/
	}
}
