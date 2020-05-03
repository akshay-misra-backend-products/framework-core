package com.bss.framework.core.schema.constants;

public class SystemConstants {

    public interface Attributes {
        /* Object Type*/
        String ATTRIBUTES = "5ea87aeec8ae3bed4679f5d9";
        String SAME_TYPE_CHILDREN = "5ea87cbcc8ae3bed4679f5da";
        String TAB_ID = "5eaaaad52e2efbf6f5c75818";

        /* Attribute Entity */
        String ATTRIBUTE_GROUP = "5ea8117bfd96fbebc61418f1";
        String ATTRIBUTE_TYPE = "5ea83e1dfd96fbebc61418f2";
        String REFERENCE_TO_OBJECT_TYPE = "5ea86df0c8ae3bed4679f5cc";
        String USAE_AS_FILTER = "5ea84103fd96fbebc61418f3";
        String FOR_CATALOG = "5ea84422fd96fbebc61418f4";
        String IS_SYSTEM = "5ea84509fd96fbebc61418f5";
        String READONLY = "5ea84655fd96fbebc61418f6";
        String REQUIRED = "5eac5c08d8415b3394a67e40";
        String HIDDEN = "5ea84695fd96fbebc61418f7";
        String MULTIPLE = "5ea846d7fd96fbebc61418f8";
        String SHOW_IN_CREATE = "5ea84788fd96fbebc61418f9";

        /* Attribute Value Entity */
        String VALUE = "5ea84a09fd96fbebc61418fa";

        /* Base Extension */
        String OBJECT_CHARACTERISTICS = "5ea84c8bfd96fbebc61418fc";


        /* Object Characteristic */
        String REF_TO_ATTRIBUTE = "5ea86e71c8ae3bed4679f5cd";
        String REF_TO_OBJECT = "5ea86fedc8ae3bed4679f5cf";
        String TEXT_VALUE = "5ea87348c8ae3bed4679f5d0";
        String NUMBER_VALUE = "5ea87386c8ae3bed4679f5d1";
        String REFERENCE_VALUE = "5ea87677c8ae3bed4679f5d5";
        String MULTI_REFERENCE_VALUE = "5ea8760fc8ae3bed4679f5d4";
        String BOOLEAN_VALUE = "5ea8784ec8ae3bed4679f5d6";
        String LIST_VALUE = "5ea87973c8ae3bed4679f5d7";
        String MULTI_LIST_VALUE = "5ea879aec8ae3bed4679f5d8";

        /* Tab */
        String ICON = "5eaa980a2e2efbf64a9f4a59";
        String REFERENCE_TO_OBJECT_TYPES = "5eaa98932e2efbf64a9f4a5a";

    }

    public static interface ObjectTypes {
        String OBJECT_TYPE = "5ea86babc8ae3bed0b307a4d";
        String ATTRIBUTE = "5e934d4567ed1fb0bcf0fca7";
        String ATTRIBUTE_GROUP = "5e934da667ed1fb0bcf0fca8";
        String ATTRIBUTE_VALUE = "5ea6c35f3fe39bd27a715a33";
        String NAVIGATION_TAB = "5eaaa5862e2efbf64a9f4a5b";
        String OBJECT_CHARACTERISTICS = "5eac03a15316dbfb9a365f3c";
    }

    public interface Objects {

    }

    public interface StringLiterals {
        String BASE_PARAMETERS = "Base Parameters";
        String AUDIT_INFORMATION = "Audit Information";
    }

    public interface TrueFalseList {
        String TRUE_ID = "80001";
        String FALSE_ID = "80002";

    }
}
