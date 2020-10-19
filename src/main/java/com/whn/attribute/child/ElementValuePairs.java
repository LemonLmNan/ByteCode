package com.whn.attribute.child;

import com.whn.common.ByteInfo;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 00:30
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class ElementValuePairs{

    private int element_name_index;
    private ElementValue element_value;

    public ElementValuePairs()throws Throwable{
        this.element_name_index = ByteInfo.readInt(2);
        this.element_value = new ElementValue();
    }

}
