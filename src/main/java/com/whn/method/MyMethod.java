package com.whn.method;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;
import com.whn.config.Config;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-14
 * Time: 22:48
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class MyMethod{

    int index;
    int name_index;
    int descriptor_index;
    String access_flags;
    int attributesCount;
    List<MyAttribute> attributes;

    public MyMethod(int index){
        this.index = index;
    }

    public MyMethod analy()throws Throwable{

        // 方法的访问标志
        String methodAccessFlags;
        int name_index, descriptor_index, attributes_count;
        // access_flag
        methodAccessFlags = ByteInfo.readHexString(2);
        // name_index
        name_index = ByteInfo.readInt(2);
        // descriptor_index
        descriptor_index = ByteInfo.readInt(2);
        // attributes_count
        attributes_count = ByteInfo.readInt(2);

        this.access_flags = Config.HEX_STR + methodAccessFlags;
        this.name_index = name_index;
        this.descriptor_index = descriptor_index;
        this.attributesCount = attributes_count;

        // 解析 attribute
        if(attributes_count > 0){
            this.attributes = Utils.newArrayList(attributes_count << 1);

            for (int ac = 0; ac<attributes_count; ac++){
                // 加一个空的
//                this.attributes.add(new MyAttribute());
                this.attributes.add(MyAttribute.getAttribute());
            }
        }

        return this;
    }

    @Override
    public String toString(){
        return Utils.toJsonString(this);
    }
}
