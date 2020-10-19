package com.whn;

import com.whn.attribute.MyAttribute;
import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.constantpool.ConstantPool;
import com.whn.constantpool.ConstantPoolEntry;
import com.whn.field.MyField;
import com.whn.method.MyMethod;
import com.whn.myinterface.MyInterface;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-10
 * Time: 23:14
 *
 * @Version: 1.0
 * @Description: 字节码解析类
 * @author: Whn
 */
public class MyClassFile{

    private String magic;
    private int minor_version;
    private int major_version;
    private int constant_pool_count;
    // 常量池
    private Map constant_pool;
    private String access_flags;
    private int this_class_index;
    private int super_class_index;
    private int interfaces_count;
    private List<MyInterface> interfaces;
    private int fields_count;
    private List<MyField> fields;
    private int methods_count;
    private List<MyMethod> methods;
    private int attributes_count;
    private List<MyAttribute> attributes;
    // 剩余未读取字节数
    private transient int available;

    MyClassFile(){

    }

    /**
     * 解析
     */
    public MyClassFile analy()throws Throwable{
        // magic - 4字节
        this.magic();
        // version - 4字节
        this.version();
        // constant_pool_count - 2字节, 常量池数量
        this.constantPoolCount();
        // constant_pool - 常量池 - ？？
        this.constantPool();
        // 访问标志 - 2字节
        this.accessFlags();
        // 自身类 - 2字节
        this.thisClass();
        // 父类 - 2字节
        this.superClass();
        // 接口数量 - 2字节
        this.interfacesCount();
        // 解析接口数组 - 数组里每个 index 都是2字节, 总共的字节数 = 接口数量 x 2(字节)
        this.interfaces();
        // 字段数量 - 2字节
        this.fieldsCount();
        // 解析字段数组 - ？？
        this.fields();
        // 方法数量 - 2字节
        this.methodCount();
        // 解析方法数组 - ？？
        this.methods();
        // attribute 数量 - 2字节
        this.attributesCount();
        // 解析 attribute 数组
        this.attributes();
        // 未读取字节数
        this.available = ByteInfo.available();
        return this;
    }

    /**
     * 魔数
     * @throws Throwable
     */
    void magic()throws Throwable{
        String magicString = ByteInfo.readHexString(4);
        if(!"cafebabe".equals(magicString)){
            throw new ClassFormatError("magic");
        }
        this.magic = magicString;
    }

    /**
     * 版本
     * @throws Throwable
     */
    void version()throws Throwable{
        int minorVersion = ByteInfo.readInt(2);
        this.minor_version = minorVersion;

        int majorVersion = ByteInfo.readInt(2);
        this.major_version = majorVersion;
    }

    /**
     * 常量池里常量的数量
     * @throws Throwable
     */
    void constantPoolCount()throws Throwable{
        int cp_count = ByteInfo.readInt(2);
        this.constant_pool_count = cp_count;
        ConstantPool.init((cp_count - 1) << 1);
    }

    /**
     * 解析常量池
     * @throws Throwable
     */
    void constantPool()throws Throwable{
        // 常量数量
        int cp_cnt = this.constant_pool_count;
        ConstantPoolEntry entry;
        for(int i = 1; i< cp_cnt; i++){
            // 解析并添加单个常量
            entry = new ConstantPoolEntry(i).analyConstantPoolEntry();
            ConstantPool.addCp(entry);
            if(null != entry){
                // Long 和 Double 需要占用2个常量数量, 但实际上是没有额外的字节, 所以添加一个空的
                if(entry.tag() == 5 || entry.tag() == 6){
                    // Whn | 2020-10-13 22:52 需要手动添加一个空的
                    ConstantPool.addCp(new ConstantPoolEntry(++i));
                }
            }
        }

        this.constant_pool = ConstantPool.allConstantEntry();
    }

    /**
     * 访问标志
     * @throws Throwable
     */
    private void accessFlags()throws Throwable{
        String accessFlag = ByteInfo.readHexString(2);
        this.access_flags = accessFlag;
    }

    /**
     * 自身类
     * @throws Throwable
     */
    private void thisClass()throws Throwable{
        int thisClassIndex = ByteInfo.readInt(2);
        this.this_class_index = thisClassIndex;
    }

    /**
     * 父类
     * @throws Throwable
     */
    private void superClass()throws Throwable{
        int superClassIndex = ByteInfo.readInt(2);
        this.super_class_index = superClassIndex;
    }

    /**
     * 接口数量
     * @throws Throwable
     */
    private void interfacesCount()throws Throwable{
        int interfacesCount = ByteInfo.readInt(2);
        this.interfaces_count = interfacesCount;
        this.interfaces = Utils.newArrayList(interfacesCount << 1);
    }

    /**
     * 解析接口数组
     * @throws Throwable
     */
    private void interfaces()throws Throwable{
        
        int interfacesCount = this.interfaces_count;
        List interfaces = this.interfaces;
        // Whn | 2020-10-10 23:03 解析接口
        // 接口是 CONSTANT_Class_info 结构
        for(int i= 0; i< interfacesCount; i++){
            // Whn | 2020-10-10 23:19 接口解析
            interfaces.add(new MyInterface(i).analy());
        }
    }

    /**
     * 字段数量
     * @throws Throwable
     */
    private void fieldsCount()throws Throwable{
        int fieldsCount = ByteInfo.readInt(2);
        this.fields_count = fieldsCount;
        this.fields = Utils.newArrayList(fieldsCount << 1);
    }

    /**
     * 字段数组
     * @throws Throwable
     */
    private void fields()throws Throwable{
        
        int fieldsCount = this.fields_count;
        List fields = this.fields;
        for(int i= 0;i< fieldsCount; i++){
            fields.add(new MyField(i).ananly());
        }
    }

    /**
     * 方法数量
     * @throws Throwable
     */
    private void methodCount()throws Throwable{
        int methodsCount = ByteInfo.readInt(2);
        this.methods_count = methodsCount;
        this.methods = Utils.newArrayList(methodsCount << 1);
    }

    /**
     * 解析方法数组
     * 方法结构
     * method_info {
     *     u2             access_flags;
     *     u2             name_index;
     *     u2             descriptor_index;
     *     u2             attributes_count;
     *     attribute_info attributes[attributes_count];
     * }
     * @throws Throwable
     */
    private void methods()throws Throwable{
        
        // 方法数量
        int methodCount = this.methods_count;

        for(int i = 0; i<methodCount; i++){
            this.methods.add(new MyMethod(i).analy());
        }
    }

    /**
     * attribute 数量
     * @throws Throwable
     */
    private void attributesCount()throws Throwable{
        int attributesCount = ByteInfo.readInt(2);
        this.attributes_count = attributesCount;
        this.attributes = Utils.newArrayList(attributesCount << 1);
    }

    /**
     * 解析 Attributes
     * @throws Throwable
     */
    private void attributes()throws Throwable{
        int attributesCount = this.attributes_count;
        for(int ac = 0; ac< attributesCount; ac++){
//            this.attributes.add(new MyAttribute());
            this.attributes.add(MyAttribute.getAttribute());
        }
    }

    /**
     * 展示该要信息
     * @throws Throwable
     */
    public void showInfo()throws Throwable{
        System.out.println(" =============== show info ===============");
        System.out.println("magic: "+magic);
        System.out.println("Minor version: "+minor_version);
        System.out.println("Major version: "+major_version);
        System.out.println("Constant pool count: "+constant_pool_count);
        System.out.println("Access flags = "+"0x"+access_flags);
        System.out.println("This class = "+"cp_info #"+this_class_index+" "+ConstantPool.getCp(ConstantPool.getCpMapInfo(this_class_index, "name_index")).getValue());
        System.out.println("Super class = "+"cp_info #"+super_class_index+" "+ConstantPool.getCp(ConstantPool.getCpMapInfo(super_class_index, "name_index")).getValue());
        System.out.println("Interfaces_count = "+interfaces_count);
        System.out.println("Fields count = "+fields_count);
        System.out.println("Methods count = "+methods_count);
        System.out.println("Attributes count = "+attributes_count);
        System.out.println("剩余未读取的字节数 = "+available);
        System.out.println(" =============== show info ===============");
    }

    public void showConstantPools(){
        System.out.println(" =============== show constant_pools ===============");
        Iterator<Map.Entry> iterator = constant_pool.entrySet().iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("Constant pool count = "+constant_pool_count);
        System.out.println(" =============== show constant_pools ===============");
    }

    public void showFields(){
        System.out.println(" =============== show fields ===============");
        List<MyField> fields = this.fields;
        for(MyField field : fields){
            System.out.println(field);
        }
        System.out.println("fields count = "+fields.size());
        System.out.println(" =============== show fields ===============");
    }

    public void showInterfaces(){
        System.out.println(" =============== show interfaces ===============");
        List<MyInterface> interfaces = this.interfaces;
        for(MyInterface oneInterface : interfaces){
            System.out.println(oneInterface);
        }
        System.out.println("interfaces count = "+interfaces.size());
        System.out.println(" =============== show interfaces ===============");
    }

    public void showMethods(){
        System.out.println(" =============== show methods ===============");
        List<MyMethod> methods = this.methods;
        for(MyMethod method : methods){
            System.out.println(method);
        }
        System.out.println("methods count = "+methods.size());
        System.out.println(" =============== show methods ===============");
    }

    public void showAttributes(){
        System.out.println(" =============== show attributes ===============");
        List<MyAttribute> attributes = this.attributes;
        for(MyAttribute attribute : attributes){
            System.out.println(attribute);
        }
        System.out.println("attributes count = "+attributes.size());
        System.out.println(" =============== show attributes ===============");
    }


}
