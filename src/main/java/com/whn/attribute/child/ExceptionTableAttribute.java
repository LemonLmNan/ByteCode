package com.whn.attribute.child;

import com.whn.common.Utils;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-15
 * Time: 22:44
 *
 * @Version: 1.0
 * @Description: Exceptions
 * @author: Whn
 */
public class ExceptionTableAttribute{
    private int start_pc;
    private int end_pc;
    private int handler_pc;
    private int catch_type;

    public ExceptionTableAttribute(int start_pc, int end_pc, int handler_pc, int catch_type){
        this.start_pc = start_pc;
        this.end_pc = end_pc;
        this.handler_pc = handler_pc;
        this.catch_type = catch_type;
    }

    @Override
    public String toString(){
        return Utils.toJsonString(this);
    }
}
