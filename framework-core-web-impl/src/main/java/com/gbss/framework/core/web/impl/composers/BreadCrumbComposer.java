package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.impl.factory.ObjectTypeRepositoryMapperFactory;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.composers.LayoutComposer;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.impl.converters.BreadCrumbConverter;
import com.gbss.framework.core.web.model.BreadCrumbConfig;
import com.gbss.framework.core.web.model.CompositeBreadCrumbConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.utils.EntityBuilder;
import com.gbss.framework.core.web.model.constants.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BreadCrumbComposer implements LayoutComposer<CompositeBreadCrumbConfig> {

    @Autowired
    ObjectTypeRepositoryMapperFactory objectTypeRepositoryFactory;

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @Autowired
    BreadCrumbConverter breadCrumbConverter;

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Autowired
    EntityBuilder entityBuilder;

    @Override  //TODO: for all cases both input will come except dynamic form case.
    public CompositeBreadCrumbConfig compose(String objectTypeId, String objectId, Layout layout) throws ObjectNotFoundException {
        ObjectType objectType = attributeSchemaService.getObjectTypeById(objectTypeId);
        MongoRepository repository = objectTypeRepositoryFactory.getBean(objectTypeId);
        CompositeBreadCrumbConfig config = new CompositeBreadCrumbConfig();
        List<BreadCrumbConfig> breadCrumbs = new ArrayList<>();
        if (Layout.FORM.equals(layout)) {
            //when form opened from list.
            if  (objectId == null || SystemConstants.Objects.FAKE_OBJECT.equals(objectId)) {
                breadCrumbs.add(breadCrumbConverter.getHome());
                if (objectType.getTabId() != null) {
                    NavigationTab navItem = applicationLayoutService.getTabById(objectType.getTabId());
                    breadCrumbs.add(breadCrumbConverter.getNavItem(navItem));
                }
            } else {
                //when form opened from details.
                breadCrumbs.add(breadCrumbConverter.getHome());
                Base base = entityBuilder.getObjectByChildOrCurrentOT(objectTypeId, objectId);
                breadCrumbs.add(breadCrumbConverter.getDetails(base));
            }
        } else if (Layout.TABLES.equals(layout) &&
                SystemConstants.ObjectTypes.NAVIGATION_TAB.equals(objectTypeId)) {
            //only Home breadcrumb available
            breadCrumbs.add(breadCrumbConverter.getHome());
            Base base = (Base) repository.findById(objectId).get();
            breadCrumbs.add(breadCrumbConverter.getDummy(base));
        } else if (Layout.DETAILS.equals(layout)) {
            //Read tabId from OT and create breadcrumb for it, along with Home.
            //Also consider opening specific sub tab, in case of composite tables.
            Base base = (Base) repository.findById(objectId).get();
            if (base.getParentId() == null) {
                breadCrumbs.add(breadCrumbConverter.getHome());
                if (objectType.getTabId() != null) {
                    NavigationTab navItem = applicationLayoutService.getTabById(objectType.getTabId());
                    breadCrumbs.add(breadCrumbConverter.getNavItem(navItem));
                }
                breadCrumbs.add(breadCrumbConverter.getDummy(base));
            } else {
                breadCrumbs.add(breadCrumbConverter.getHome());
                if (objectType.getParentId() == null) {
                    Base parent = (Base) repository.findById(base.getParentId()).get();
                    breadCrumbs.add(breadCrumbConverter.getDetails(parent));
                } else {
                    ObjectType parentOT = attributeSchemaService.getObjectTypeById(objectType.getParentId());
                    MongoRepository parentRepo = objectTypeRepositoryFactory.getBean(parentOT.getId());
                    Base parent = (Base) parentRepo.findById(base.getParentId()).get();
                    breadCrumbs.add(breadCrumbConverter.getDetails(parent));
                }
                breadCrumbs.add(breadCrumbConverter.getDummy(base));
            }
        }

        config.setBreadCrumbs(breadCrumbs);

        return config;
    }
}
