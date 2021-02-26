package com.gbss.framework.core.impl.comparators;


import com.gbss.framework.core.model.entities.Base;

import java.util.Comparator;

public class OrderNumberComparator implements Comparator<Base> {

    @Override
    public int compare(Base b1, Base b2) {
        if (b1.getOrder() < b2.getOrder()) return -1;
        if (b1.getOrder() > b2.getOrder()) return 1;
        else return 0;
    }
}
