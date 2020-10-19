package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.child.ExceptionTableAttribute;
import com.whn.attribute.MyAttribute;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 10:23
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class Code_attribute extends MyAttribute{

    // 2
    private int max_stack;
    // 2
    private int max_locals;
    // 4
    private int code_length;
    // byte[code_length]
    private byte[] code;
    // 2
    private int exception_table_length;
    // size = exception_table_length
    private List<ExceptionTableAttribute> exception_table;
    // 2
    private int attributes_count;
    // size = attributes_count;
    private List<MyAttribute> attributes;

    public Code_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        System.out.println("attribute_length = "+attribute_length);
        this.max_stack = ByteInfo.readInt(2);
        this.max_locals = ByteInfo.readInt(2);
        long code_length = ByteInfo.readLong(4);
        // 这里暂时用强转转int
        byte[] codeBytes = ByteInfo.readBytes((int)code_length);
        // Whn | 2020-10-15 23:11 接下来是 code数组的处理 , 表示代码字节

        // 到这里是 14 - 6(前面两个通用字段长度和6)
        int start_pc, end_pc, handler_pc, catch_type;
        int exception_table_length = ByteInfo.readInt(2);
        this.exception_table_length = exception_table_length;
        System.out.println("exception_table_length = "+exception_table_length);
        if(exception_table_length > 0){
            this.exception_table = Utils.newArrayList(exception_table_length << 1);
            for (int cIndex = 0; cIndex<exception_table_length; cIndex++){
                start_pc = ByteInfo.readInt(2);
                end_pc = ByteInfo.readInt(2);
                handler_pc = ByteInfo.readInt(2);
                catch_type = ByteInfo.readInt(2);

                this.exception_table.add(new ExceptionTableAttribute(start_pc, end_pc, handler_pc, catch_type));
            }
        }

        // attribute_count 的开始下标
        // attribute 数量
        int attribute_count = ByteInfo.readInt(2);
        this.attributes_count = attributes_count;
        if(attribute_count > 0){
            this.attributes = Utils.newArrayList(attribute_count << 1);
        }

        // 读取 attributes
        this.attributes = this.readAttributes(this.attributes, attribute_count);
    }

    public List<MyAttribute> readAttributes(List attributes, int attributes_count)throws Throwable{

        int attribute_name_index;
        long attribute_length;
        byte[] attributes_info;

        System.out.println("attributes_count = "+attributes_count);
        if(attributes_count > 0 && null != attributes){

            if(null == attributes || attributes.isEmpty()){
                attributes = Utils.newArrayList(attributes_count << 1);
            }

            for (int childIndex = 0; childIndex<attributes_count; childIndex++){
                attribute_name_index = ByteInfo.readInt(2);
                attribute_length = ByteInfo.readLong(4);
                // 暂时用强转转int
                attributes_info = ByteInfo.readBytes((int)attribute_length);

                System.out.println("Child Attribute.attribute_name_index = "+attribute_name_index);
                System.out.println("Child Attribute.attribute_length = "+attribute_length);

                attributes.add(new MyAttribute(attribute_name_index, attribute_length));
            }
        }

        return attributes;
    }
}
