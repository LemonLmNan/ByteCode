package com.whn.field;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;
import com.whn.config.Config;
import com.whn.constantpool.ConstantPool;
import com.whn.constantpool.ConstantPoolEntry;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-13
 * Time: 23:16
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class MyField{

    int index;
    String field;
    int name_index;
    int descriptor_index;
    String access_flags;
    int attributesCount;
    List<MyAttribute> attributes;

    public MyField(int index){
        this.index = index;
    }

    public MyField ananly()throws Throwable{
        String access_flag;
        int name_index, descriptorIndex, attributes_count;

        // tag
        access_flag = ByteInfo.readHexString(2);

        // one field
        name_index = ByteInfo.readInt(2);
        ConstantPoolEntry fieldNameEntry = ConstantPool.getCp(name_index);
        this.field = fieldNameEntry.getValue();

        // descriptorIndex
        descriptorIndex = ByteInfo.readInt(2);

        // attributes_count
        attributes_count = ByteInfo.readInt(2);

        this.access_flags = Config.HEX_STR+access_flag;
        this.name_index = name_index;
        this.descriptor_index = descriptorIndex;
        this.attributesCount = attributes_count;

        // 解析 attribute
        if(attributes_count > 0){
            this.attributes = Utils.newArrayList(attributes_count << 1);

            for (int ac = 0; ac<attributes_count; ac++){
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
