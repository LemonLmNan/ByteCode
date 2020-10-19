package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.attribute.MyAttribute;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 19:06
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class EnclosingMethod extends MyAttribute{
    private int class_index;
    private int method_index;

    public EnclosingMethod(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        this.class_index = ByteInfo.readInt(2);
        this.method_index = ByteInfo.readInt(2);

    }
}
