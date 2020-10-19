package com.whn.attribute.parent;

import com.whn.attribute.MyAttribute;
import com.whn.attribute.child.InnerClass;
import com.whn.common.ByteInfo;
import com.whn.common.Utils;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-19
 * Time: 20:13
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class InnerClasses_attribute extends MyAttribute{

    List<InnerClass> classes;

    public InnerClasses_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        int number_of_classes = ByteInfo.readInt(2);
        System.out.println("number_of_classes = "+number_of_classes);
        List<InnerClass> classes;

        if(number_of_classes > 0){
            classes = Utils.newArrayList(number_of_classes << 1);
            for (int num = 0; num < number_of_classes; num++){
                classes.add(new InnerClass());
            }
            this.classes = classes;
        }
    }
}
