package com.gbss.framework.core.web.impl.repositories;

import com.gbss.framework.core.meta.annotations.ObjectType;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.web.model.NavigationTab;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Repository
@ObjectType(SystemConstants.ObjectTypes.NAVIGATION_TAB)
public interface TabRepository extends MongoRepository<NavigationTab, String> {

    List<NavigationTab> findByParentId(String parentId);
}
