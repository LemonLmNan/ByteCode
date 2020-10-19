package com.whn.attribute.parent;

import com.whn.attribute.MyAttribute;
import com.whn.attribute.child.ElementValue;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 18:45
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class AnnotationDefault_attribute extends MyAttribute{

    private ElementValue default_value;

    public AnnotationDefault_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        this.default_value = new ElementValue();
    }
}
