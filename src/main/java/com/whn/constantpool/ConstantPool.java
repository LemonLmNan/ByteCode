package com.whn.constantpool;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-12
 * Time: 22:20
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public final class ConstantPool{

    private static Map<Object, ConstantPoolEntry> constantPoolMap;

    public static void init(int size){
        constantPoolMap = new LinkedHashMap(size);
    }

    public static void addCp(ConstantPoolEntry entry){
        constantPoolMap.put(entry.getIndex(), entry);
    }

    public static ConstantPoolEntry getCp(Object key){
        ConstantPoolEntry entry = constantPoolMap.get(key);
        if(null == entry){
            throw new ClassFormatError("index: "+key);
        }

        return entry;
    }

    public static Object getCpMapInfo(Object key, String field){
        ConstantPoolEntry entry = getCp(key);
        return entry.getFromMap(field);
    }

    public static Map allConstantEntry(){
        return constantPoolMap;
    }
}
