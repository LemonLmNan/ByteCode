package com.whn.attribute.child;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 00:28
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class AnnotationAttribute{
    private int type_index;
    private int num_element_value_pairs;
    private List<ElementValuePairs> element_value_pairs;

    public AnnotationAttribute()throws Throwable{
        this.type_index = ByteInfo.readInt(2);
        int num_element_value_pairs = ByteInfo.readInt(2);
        this.num_element_value_pairs = num_element_value_pairs;

        if(num_element_value_pairs > 0){
            this.element_value_pairs = Utils.newArrayList(num_element_value_pairs << 1);
            for (int num = 0; num < num_element_value_pairs; num++){
                this.element_value_pairs.add(new ElementValuePairs());
            }
        }

    }
}
