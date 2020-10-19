package com.whn.attribute.child;

import com.whn.common.ByteInfo;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 00:54
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class EnumConstValue{

    private int type_name_index;
    private int const_name_index;

    public EnumConstValue()throws Throwable{
        this.type_name_index = ByteInfo.readInt(2);
        this.const_name_index = ByteInfo.readInt(2);

    }
}
