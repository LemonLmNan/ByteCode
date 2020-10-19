package com.whn.attribute;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.parent.*;
import com.whn.constantpool.ConstantPool;
import com.whn.constantpool.ConstantPoolEntry;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-14
 * Time: 23:05
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class MyAttribute{

    protected String attributeName;
    protected int attribute_name_index;
    protected long attribute_length;
    public MyAttribute(int attribute_name_index, long attribute_length){
        this.attribute_name_index = attribute_name_index;
        this.attribute_length = attribute_length;
        this.attributeName = ConstantPool.getCp(attribute_name_index).getValue();
    }

    public static MyAttribute getAttribute()throws Throwable{
        // attribute_name_index
        int attribute_name_index = ByteInfo.readInt(2);
        // attribute_length
        long attribute_length = ByteInfo.readLong(4);
        // 根据 attribute_name_index 读取常量, 根据常量值解析不同的数据
        //        byte[] bytes3 = ByteInfo.readBytes(attribute_length);
        // 解析不同的 Attribute
        MyAttribute attribute = differentAttribute(attribute_name_index, attribute_length);
        return attribute;
    }

    /**
     * ConstantValue
     * Code
     * StackMapTable
     * Exceptions
     * InnerClasses
     * EnclosingMethod
     * Synthetic
     * Signature
     * SourceFile
     * SourceDebugExtension
     * LineNumberTable
     * LocalVariableTable
     * LocalVariableTypeTable
     * Deprecated
     * RuntimeVisibleAnnotations
     * RuntimeInvisibleAnnotations
     * RuntimeVisibleParameterAnnotations
     * RuntimeInvisibleParameterAnnotations
     * AnnotationDefault
     * BootstrapMethods
     *
     * @throws Throwable
     */
    public static MyAttribute differentAttribute(int attribute_name_index, long attribute_length)throws Throwable{
        MyAttribute attribute = null;
        System.out.println("attribute_length = "+attribute_length);
        // 做不同的解析处理
        String attributeName = getConstantName(attribute_name_index);
        System.out.println("attribute_name_index = "+attribute_name_index+", attributeName = "+attributeName);

        // 用于遍历
        int childIndex = 0;
        System.out.println("i am at "+attributeName);
        switch(attributeName){
            case "ConstantValue":
                int constantvalue_index = ByteInfo.readInt(2);
                // 这里是2字节
                System.out.println("constantvalue_index = "+constantvalue_index);
                break;
            case "Code":
                attribute = new Code_attribute(attribute_name_index, attribute_length);
                break;
            case "StackMapTable":
                int number_of_entries = ByteInfo.readInt(2);
                System.out.println("number_of_entries = "+number_of_entries);
                // TODO: 处理 entry

                System.out.println("===================");
                break;
            case "Exceptions":
                attribute = new Exceptions_attribute(attribute_name_index, attribute_length);
                break;
            case "InnerClasses":
                attribute = new InnerClasses_attribute(attribute_name_index, attribute_length);
                break;
            case "EnclosingMethod":
                attribute = new EnclosingMethod(attribute_name_index, attribute_length);
                break;
            case "Synthetic":
                // 这个没有额外字节
                break;
            case "Signature":
                int signature_index = ByteInfo.readInt(2);
                System.out.println("signature_index = "+signature_index);
                break;
            case "SourceFile":attribute = new SourceFile_attribute(attribute_name_index, attribute_length);
                break;
            case "SourceDebugExtension":
                String sourceDebugString = ByteInfo.readString(attribute_length);
                System.out.println("sourceDebugString = "+sourceDebugString);
                break;
            case "LineNumberTable":
                attribute = new LineNumberTable_attribute(attribute_name_index, attribute_length);
                break;
            case "LocalVariableTable":
                attribute = new LocalVariableTable_attribute(attribute_name_index, attribute_length);
                break;
            case "LocalVariableTypeTable":
                attribute = new LocalVariableTypeTable_attribute(attribute_name_index, attribute_length);
                break;
            case "Deprecated":
                // Deprecated 的结构中没有其它多余的字符
                break;
            case "RuntimeVisibleAnnotations":attribute = new Annotation_attributes(attribute_name_index, attribute_length);
                break;
            case "RuntimeInvisibleAnnotations": attribute = new Annotation_attributes(attribute_name_index, attribute_length);
                break;
            case "RuntimeVisibleParameterAnnotations":
                attribute = new RuntimeVisibleParameterAnnotations_attribute(attribute_name_index, attribute_length);
                break;
            case "RuntimeInvisibleParameterAnnotations":
                // Whn | 2020-10-16 13:09 拿到返回值后的处理
                attribute = new RuntimeInvisibleParameterAnnotations_attribute(attribute_name_index, attribute_length);
                break;
            case "AnnotationDefault":
                attribute = new AnnotationDefault_attribute(attribute_name_index, attribute_length);
                break;
            case "BootstrapMethods":
                attribute = new BootstrapMethods_attribute(attribute_name_index, attribute_length);
//                int num_bootstrap_methods = ByteInfo.readInt(2);
//                int bootstrap_method_ref, num_bootstrap_arguments;
//                int num_bootstrap_arguments_index, num_start_index, num_cp_index, lastChildEntrySize = 0;
//
//                for(childIndex = 0; childIndex < num_bootstrap_methods; childIndex++){
//
//                    bootstrap_method_ref = ByteInfo.readInt(2);
//                    num_bootstrap_arguments = ByteInfo.readInt(2);
//
//                    System.out.println("bootstrap_method_ref = "+bootstrap_method_ref);
//                    System.out.println("num_bootstrap_arguments = "+num_bootstrap_arguments);
//
//                    // 里面的每一个都是常量池中的下标
//                    for(num_bootstrap_arguments_index = 0; num_bootstrap_arguments_index < num_bootstrap_arguments; num_bootstrap_arguments_index++){
//                        num_cp_index = ByteInfo.readInt(2);
//                        System.out.println(num_bootstrap_arguments_index+"num_cp_index = "+num_cp_index);
//                    }
//                }
                break;
            default:
                throw new ClassFormatError("Attribute: "+attributeName);
        }
        System.out.println("------------------");
        return attribute;
    }



    /**
     *
     * @param attribute_name_index 常量池中的下标
     * @return
     */
    private static String getConstantName(int attribute_name_index){
        ConstantPoolEntry entry = ConstantPool.getCp(attribute_name_index);
        return String.valueOf(entry.getValue());
    }

    @Override
    public String toString(){
        return Utils.toJsonString(this);
    }
}
