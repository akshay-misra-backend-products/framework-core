package com.gbss.framework.core.model.constants;

public class SystemConstants {

    public interface Attributes {

        /* Base */
        String ID = "-1";
        String OBJECT_TYPE_ID = "-2";
        String NAME = "-3";
        String PUBLIC_NAME = "-4";
        String DESCRIPTION = "-5";
        String PARENT_ID = "-6";
        String PARENT_TYPE_ID = "-7";
        String VERSION = "-8";
        String ORDER = "-9";
        String CREATED_AT = "-10";
        String LAST_MODIFIED_AT = "-11";

        /* Object Type*/
        String ATTRIBUTES = "5ea87aeec8ae3bed4679f5d9";
        String SAME_TYPE_CHILDREN = "5ea87cbcc8ae3bed4679f5da";
        String TAB_ID = "5eaaaad52e2efbf6f5c75818";
        String COLLECTION_NAME = "5ee69015862e3f0ab57bd2d7";

        /* Attribute Entity */
        String ATTRIBUTE_GROUP = "5ea8117bfd96fbebc61418f1";
        String ATTRIBUTE_TYPE = "5ea83e1dfd96fbebc61418f2";
        String REFERENCE_TO_OBJECT_TYPE = "5ea86df0c8ae3bed4679f5cc";
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
        String IS_CONTAINER = "5eca9489c4b81f159cf43f59";
        String MODULE = "605339aaa391a46b484750e4";

        /* Dynamic Object */
        String DYNAMIC_PARAMETERS = "5f11ec17fcbf48118eb7daad";

        /* Locale */
        String CURRENCY = "5f4179bbb04d4e2b37d435cf";

        /* Currency */
        String SYMBOL = "5f417825b04d4e2b37d435cd";
        String CODE = "5f417883b04d4e2b37d435ce";

        /* Module */
        String CORE_MODULE = "5f2ea5a224ce613564b204b0";
        String ATTRIBUTE_EXTENSION = "6044f02449e76459a2a544c9";


        /* Service */
        String SERVICE_NAME = "5f0b7910a6d60f42989c292a";
        String SERVICE_TYPE = "5f398d7a3a02c963344e9074";

    }

    public interface AttributeValue {
        String SERVICE_TYPE_MANAGEMENT = "5f398f613a02c963344e9075";
        String SERVICE_TYPE_SEARCH = "5f398fc93a02c963344e9076";
    }

    public interface ObjectTypes {
        String OBJECT_TYPE = "5ea86babc8ae3bed0b307a4d";
        String ATTRIBUTE = "5e934d4567ed1fb0bcf0fca7";
        String ATTRIBUTE_GROUP = "5e934da667ed1fb0bcf0fca8";
        String ATTRIBUTE_VALUE = "5ea6c35f3fe39bd27a715a33";
        String NAVIGATION_TAB = "5eaaa5862e2efbf64a9f4a5b";
        String OBJECT_CHARACTERISTICS = "5eac03a15316dbfb9a365f3c";
        String LOCALE = "5f41766eb04d4e2b37d435cb";
        String CURRENCY = "5f4176d7b04d4e2b37d435cc";
        String MODULE = "5f0b7828a6d60f42989c2929";
        String MICROSERVICE = "5f2ef701d17e18357b7efb05";
    }

    public interface Objects {
        String FAKE_OBJECT = "5ed3d8c70c349b61e1d9631c";
        String PARENT_ID_FAKE = "604da78b109a5a08e954055a";
        String PARENT_OBJECT_TYPE_ID_FAKE = "5eed2522862e3f23dbb0dbac";
        String DYNAMIC_OBJECTS_NAVIGATION_ID = "5ee7aa7a862e3f0d4e19fc3c";
        String FRAMEWORK_CORE_MODULE_ID = "603aad6fe1d7a507c3ba595c";
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
