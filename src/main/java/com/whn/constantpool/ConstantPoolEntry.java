package com.whn.constantpool;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-12
 * Time: 22:20
 *
 * @Version:
 * @Description: 常量池常量
 * @author: Whn
 */
public class ConstantPoolEntry {
    private final Map<Object, Object> map = new LinkedHashMap<>();
    int cp_index;
    int tag;

    public ConstantPoolEntry(int cp_index){
        this.cp_index = cp_index;
        map.put("index", cp_index);
    }

    public int getIndex(){
        return this.cp_index;
    }

    /**
     * 获取常量池标签
     * @return
     */
    public int tag(){
        return this.tag;
    }

    public ConstantPoolEntry analyConstantPoolEntry() throws Throwable{
        int tag = ByteInfo.readInt(1);
        map.put("tag", tag);
        this.tag = tag;

        int index1, index2, length;
        String constantString;
        Object value;

        switch(tag){
            case 1:
                length = ByteInfo.readInt(2);
                constantString = ByteInfo.readString(length);
                value = constantString;
                map.put("tag", tag);
                map.put("constant_type", "CONSTANT_Utf8_info");
                map.put("length", length);
                map.put("value", value);
                break;
            case 3:
                value = ByteInfo.readInt(4);
                map.put("constant_type", "CONSTANT_Integer_info");
                map.put("value", value);
                break;
            case 4:
                // 解析 Float
//                String floatString = ByteInfo.readHexString(4);
//                tmpLong = Long.parseLong(floatString, 16);
//                value = Float.intBitsToFloat(tmpLong.intValue());
                value = ByteInfo.readFloat();
                map.put("constant_type", "CONSTANT_Float_info");
                map.put("value", value);
                break;
            case 5:
                // 解析 long
//                tmpHigh = ByteInfo.readInt(4);
//                high =  tmpHigh & 0xffffffffL;
//
//                tmpLow = ByteInfo.readInt(4);
//                low =  tmpLow & 0xffffffffL;
//
//                long longResult = (high << 32) | low;
                long longResult = ByteInfo.readLong();
                value = longResult;

                map.put("constant_type", "CONSTANT_Long_info");
                map.put("value", value);

                break;
            case 6:
                // 解析 double
                Double doubleResult;
//                tmpHigh = ByteInfo.readInt(4);
//                high =  tmpHigh & 0xffffffffL;
//
//                tmpLow = ByteInfo.readInt(4);
//                low =  tmpLow & 0xffffffffL;
//
//                long tmpLongResult = (high << 32) + low;
//                long tmpLongResult = ByteInfo.readLong();
//                doubleResult = Double.longBitsToDouble(tmpLongResult);
                value = ByteInfo.readDouble();

                map.put("constant_type", "CONSTANT_Double_info");
                map.put("value", value);
                break;
            case 7:
                index1 = ByteInfo.readInt(2);
                map.put("constant_type", "CONSTANT_Class_info");
                map.put("name_index", index1);
                break;
            case 8:
                // string
                index1 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_String_info");
                map.put("string_index", index1);
                break;
            case 9:
                // 解析字段的
                index1 = ByteInfo.readInt(2);
                index2 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_Fieldref_info");
                map.put("class_index", index1);
                map.put("name_and_type_index", index2);
                break;
            case 10:
                // 解析方法的
                index1 = ByteInfo.readInt(2);
                index2 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_Methodref_info");
                map.put("class_index", index1);
                map.put("name_and_type_index", index2);
                break;
            case 11:
                // 解析接口方法的
                index1 = ByteInfo.readInt(2);
                index2 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_InterfaceMethodref_info");
                map.put("class_index", index1);
                map.put("name_and_type_index", index2);
                break;
            case 12:
                // 解析方法的
                index1 = ByteInfo.readInt(2);
                index2 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_NameAndType_info");
                map.put("name_index", index1);
                map.put("descriptor_index", index2);
                break;
            case 15:
                // 解析方法句柄
                // reference_kind
                index1 = ByteInfo.readInt(1);
                // reference_index
                index2 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_MethodHandle_info");
                map.put("reference_kind", index1);
                map.put("reference_index", index2);
                break;
            case 16:
                value = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_MethodType_info");
                map.put("descriptor_index", value);
                break;
            case 18:
                // 解析方法的
                index1 = ByteInfo.readInt(2);
                index2 = ByteInfo.readInt(2);

                map.put("constant_type", "CONSTANT_InvokeDynamic_info");
                map.put("bootstrap_method_attr_index", index1);
                map.put("name_and_type_index", index2);
                break;
            default:
                throw new ClassFormatError("unknown tag : "+tag);
        }
        return this;
    }

    public Map getMap(){
        return this.map;
    }

    public Object getFromMap(Object key){
        return this.map.get(key);
    }

    public String getValue(){
        return String.valueOf(this.map.get("value"));
    }
    @Override
    public String toString(){
        return Utils.toJsonString(map);
    }
}
