package com.whn.attribute.child;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 00:56
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class ArrayValue{

    private int num_values;
    private List<ElementValue> values;

    public ArrayValue()throws Throwable{
        this.num_values = ByteInfo.readInt(2);
        this.values = Utils.newArrayList(num_values << 1);

    }

}
