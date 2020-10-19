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
public class ElementValue{

    private int tag;
    private Union value;

    public ElementValue()throws Throwable{
        this.tag = ByteInfo.readInt(1);
        this.value = new Union();
    }
}
