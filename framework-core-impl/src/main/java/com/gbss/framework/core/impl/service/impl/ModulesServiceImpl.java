package com.gbss.framework.core.impl.service.impl;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.service.api.ModulesService;
import com.gbss.framework.core.impl.repositories.ModuleRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.impl.repositories.ServiceRepository;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.Microservice;
import com.gbss.framework.core.model.entities.Module;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
@Service
public class ModulesServiceImpl extends ApplicationAuditServiceImpl implements ModulesService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Override
    public List<Module> getModules() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return moduleRepository.findAll(sortByName);
    }

    @Override
    public Module getModuleById(String id) {
        return moduleRepository.findById(id).get();
    }

    @Override
    public Module getModuleByObjectType(ObjectType objectType) {
        if (objectType.getParentId() == null) {
            objectType = attributeSchemaService.getObjectTypeById(objectType.getId());
        }
        return (Module) findModuleRecursively(objectType);
    }

    private Module findModuleRecursively(Base base) {
        System.out.println("... findModuleRecursively, base: "+ base);
        Module module = null;
        ObjectType objectType = attributeSchemaService.getObjectTypeById(base.getParentId());
        if (base.getParentId() != null && objectType == null) {
            module = moduleRepository.findById(base.getParentId()).get();
        } else {
            module = findModuleRecursively(objectType);
        }

        System.out.println("... findModuleRecursively, module: "+ module);
        return module;
    }

    @Override
    public Module createModule(Module module) {
        System.out.println("... createModule, module: "+ module);
        module.setObjectTypeId(SystemConstants.ObjectTypes.MODULE);
        return moduleRepository.save(module);
    }

    @Override
    public Module updateModule(Module module) {
        System.out.println("... updateModule, module: "+ module);
        Optional<Module> moduleOp = moduleRepository.findById(module.getId());
        handleAudit(moduleOp.get(), module);
        return moduleRepository.save(module);
    }

    @Override
    public boolean deleteModule(String id) {
        System.out.println("... deleteModule, id: "+ id);
        Optional<Module> moduleOp = moduleRepository.findById(id);
        if (moduleOp != null) {
            moduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Microservice> getMicroservices() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return serviceRepository.findAll(sortByName);
    }

    @Override
    public List<Microservice> getMicroservicesByParentId(String parentId) {
        return serviceRepository.findByParentId(parentId);
    }

    @Override
    public Microservice getMicroservicesByParentIdAndServiceType(String parentId,
                                                                       String serviceType) {
        return serviceRepository.findByParentIdAndServiceType(parentId, serviceType);
    }

    @Override
    public String gerCrudServiceNameByObjectType(ObjectType objectType) {
        Module module = getModuleByObjectType(objectType);
        Microservice service = getMicroservicesByParentIdAndServiceType(module.getId(),
                SystemConstants.AttributeValue.SERVICE_TYPE_MANAGEMENT);
        return service.getServiceName();
    }

    @Override
    public Microservice getMicroserviceById(String id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public Microservice createMicroservice(Microservice service) {
        System.out.println("... createMicroservice, service: "+ service);
        service.setObjectTypeId(SystemConstants.ObjectTypes.MICROSERVICE);
        return serviceRepository.save(service);
    }

    @Override
    public Microservice updateMicroservice(Microservice service) {
        System.out.println("... updateMicroservice, service: "+ service);
        Optional<Microservice> serviceOp = serviceRepository.findById(service.getId());
        /** TODO
        * If there is an update in 'Service Name' attribute we have
        * to update all Object Type's API URL under current module.
        */
        handleAudit(serviceOp.get(), service);
        return serviceRepository.save(service);
    }

    @Override
    public boolean deleteMicroservice(String id) {
        System.out.println("... deleteMicroservice, id: "+ id);
        Optional<Microservice> serviceOp = serviceRepository.findById(id);
        if (serviceOp != null) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
