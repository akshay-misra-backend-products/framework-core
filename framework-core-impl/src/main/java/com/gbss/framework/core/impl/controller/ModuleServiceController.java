package com.gbss.framework.core.impl.controller;

import com.gbss.framework.core.api.service.api.ModulesService;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Microservice;
import com.gbss.framework.core.model.entities.Module;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
@RestController
@RequestMapping("/application/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleServiceController {

    @Autowired
    ModulesService modulesService;

    @GetMapping("/" + SystemConstants.ObjectTypes.MODULE + "/load/all")
    public List<Module> getModules() {
        return modulesService.getModules();
    }

    @GetMapping(value="/" + SystemConstants.ObjectTypes.MODULE + "/load/{id}")
    public Module getModuleById(@PathVariable("id") String id) {
        return modulesService.getModuleById(id);
    }

    @PostMapping("/" + SystemConstants.ObjectTypes.MODULE + "/add")
    public Module createModule(@RequestBody Module module) {
        return modulesService.createModule(module);
    }

    @PutMapping(value="/" + SystemConstants.ObjectTypes.MODULE + "/update")
    public Module updateModule(@Valid @RequestBody Module module) {
        return modulesService.updateModule(module);
    }

    @DeleteMapping(value="/" + SystemConstants.ObjectTypes.MODULE + "/delete/{id}")
    public boolean deleteModule(@PathVariable("id") String id) {
        return modulesService.deleteModule(id);
    }

    @GetMapping("/" + SystemConstants.ObjectTypes.MICROSERVICE + "/load/all")
    public List<Microservice> getMicroservices() {
        return modulesService.getMicroservices();
    }

    @GetMapping(value="/" + SystemConstants.ObjectTypes.MICROSERVICE + "/load/by/parent/{parentId}")
    public List<Microservice> getMicroservicesByParentId(@PathVariable("parentId") String parentId) {
        return modulesService.getMicroservicesByParentId(parentId);
    }

    @GetMapping(value="/" + SystemConstants.ObjectTypes.MICROSERVICE + "/load/{id}")
    public Microservice getMicroserviceById(@PathVariable("id") String id) {
        return modulesService.getMicroserviceById(id);
    }

    @PostMapping("/" + SystemConstants.ObjectTypes.MICROSERVICE + "/add")
    public Microservice createMicroservice(@RequestBody Microservice service) {
        return modulesService.createMicroservice(service);
    }

    @PutMapping(value="/" + SystemConstants.ObjectTypes.MICROSERVICE + "/update")
    public Microservice updateMicroservice(@Valid @RequestBody Microservice service) {
        return modulesService.updateMicroservice(service);
    }

    @DeleteMapping(value="/" + SystemConstants.ObjectTypes.MICROSERVICE + "/delete/{id}")
    public boolean deleteMicroservice(@PathVariable("id") String id) {
        return modulesService.deleteMicroservice(id);
    }

}
