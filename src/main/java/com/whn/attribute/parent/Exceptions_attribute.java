package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 12:46
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class Exceptions_attribute extends MyAttribute{
    private int number_of_exceptions;
    private List<Integer> exception_index_table;
    public Exceptions_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        this.number_of_exceptions = ByteInfo.readInt(2);
        if(number_of_exceptions >0){
            exception_index_table = Utils.newArrayList(number_of_exceptions << 1);
            for(int num =0; num < number_of_exceptions; num++){
                exception_index_table.add(ByteInfo.readInt(2));
            }
        }

    }
}
