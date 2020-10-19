package com.whn.myinterface;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.constantpool.ConstantPool;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-13
 * Time: 23:24
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class MyInterface{

    int index;
    String interfaceName;
    int cp_index;

    public MyInterface(int index){
        this.index = index;
    }

    public MyInterface analy()throws Throwable{
        this.cp_index = ByteInfo.readInt(2);
        // 方法的话指向 class_info ,class_info 存储 name_index
        Object name_index = ConstantPool.getCpMapInfo(cp_index, "name_index");
        this.interfaceName = String.valueOf(ConstantPool.getCp(name_index).getValue());
        return this;
    }

    @Override
    public String toString(){
        return Utils.toJsonString(this);
    }
}
