package com.whn.attribute.child;

import com.whn.common.ByteInfo;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 19:09
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class Line_number_table{

    private int start_pc;
    private int line_number;

    public Line_number_table()throws Throwable{
        this.start_pc = ByteInfo.readInt(2);
        this.line_number = ByteInfo.readInt(2);
    }
}
