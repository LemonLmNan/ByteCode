package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.attribute.MyAttribute;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 10:22
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class ConstantValue_attribute extends MyAttribute{

    private int constantvalue_index;

    public ConstantValue_attribute(int attribute_name_index, int attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        this.constantvalue_index = ByteInfo.readInt(2);
    }
}
