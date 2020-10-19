package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.attribute.MyAttribute;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 12:43
 *
 * @Version: 1.0
 * @Description:
 * @author: Whn
 */
public class SourceFile_attribute extends MyAttribute{

    private int sourcefile_index;

    public SourceFile_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        this.sourcefile_index = ByteInfo.readInt(2);
    }
}
