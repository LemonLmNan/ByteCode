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
public class Union{
    private int const_value_index;
    private EnumConstValue enum_const_value;
    private int class_info_index;
    private AnnotationAttribute annotation_value;
    private ArrayValue array_value;

    public Union()throws Throwable{
        this.const_value_index = ByteInfo.readInt(2);
        this.enum_const_value = new EnumConstValue();
        this.class_info_index = ByteInfo.readInt(2);
        this.annotation_value = new AnnotationAttribute();
        this.array_value = new ArrayValue();
    }
}
