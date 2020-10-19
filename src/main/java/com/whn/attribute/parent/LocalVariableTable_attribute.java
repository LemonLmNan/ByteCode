package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;
import com.whn.attribute.child.Local_variable_table;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 18:52
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class LocalVariableTable_attribute extends MyAttribute{
    int local_variable_table_length;
    private List<Local_variable_table> local_variable_table;

    public LocalVariableTable_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        int local_variable_table_length = ByteInfo.readInt(2);
        this.local_variable_table_length = local_variable_table_length;

        if(local_variable_table_length > 0){
            List<Local_variable_table> list = Utils.newArrayList(local_variable_table_length << 1);
            for(int num = 0; num < local_variable_table_length; num++){
                list.add(new Local_variable_table());
            }
            this.local_variable_table = list;
        }

    }
}
