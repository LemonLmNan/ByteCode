package com.whn.attribute.child;

import com.whn.common.ByteInfo;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-19
 * Time: 20:15
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class InnerClass{

    private int inner_class_info_index;
    private int outer_class_info_index;
    private int inner_name_index;
    private int inner_class_access_flags;

    public InnerClass()throws Throwable{
        this.inner_class_info_index = ByteInfo.readInt(2);
        this.outer_class_info_index = ByteInfo.readInt(2);
        this.inner_name_index = ByteInfo.readInt(2);
        this.inner_class_access_flags = ByteInfo.readInt(2);

    }
    
}
