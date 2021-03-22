package com.gbss.framework.core.api.service.api;

import com.gbss.framework.core.model.entities.Microservice;
import com.gbss.framework.core.model.entities.Module;
import com.gbss.framework.core.model.entities.ObjectType;

import java.util.List;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
public interface ModulesService {

    List<Module> getModules();

    Module getModuleById(String id);

    Module getModuleByObjectType(ObjectType objectType);

    Module createModule(Module module);

    Module updateModule(Module module);

    boolean deleteModule(String id);

    List<Microservice> getMicroservices();

    Microservice getMicroserviceById(String id);

    List<Microservice> getMicroservicesByParentId(String parentId);

    Microservice getMicroservicesByParentIdAndServiceType(String parentId,
                                                                String serviceType);

    String gerCrudServiceNameByObjectType(ObjectType objectType);

    Microservice createMicroservice(Microservice objectType);

    Microservice updateMicroservice(Microservice objectType);

    boolean deleteMicroservice(String id);
}
