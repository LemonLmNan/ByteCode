package com.whn.attribute.child;

import com.whn.common.ByteInfo;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 18:54
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class Local_variable_type_table{

    private int start_pc;
    private int length;
    private int name_index;
    private int signature_index;
    private int index;

    public Local_variable_type_table()throws Throwable{
        this.start_pc = ByteInfo.readInt(2);
        this.length = ByteInfo.readInt(2);
        this.name_index = ByteInfo.readInt(2);
        this.signature_index = ByteInfo.readInt(2);
        this.index = ByteInfo.readInt(2);
    }
}
