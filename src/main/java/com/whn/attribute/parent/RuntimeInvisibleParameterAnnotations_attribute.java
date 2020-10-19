package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 18:28
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class RuntimeInvisibleParameterAnnotations_attribute extends MyAttribute{
    private int num_parameters;
    private List<Annotation_attributes> parameter_annotations;

    public RuntimeInvisibleParameterAnnotations_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        int num_parameters = ByteInfo.readInt(1);
        this.num_parameters = num_parameters;

        if(num_parameters > 0){
            parameter_annotations = Utils.newArrayList(num_parameters >> 1);
            int name_index, att_length;
            for(int num = 0; num < num_parameters; num++){
                name_index = ByteInfo.readInt(2);
                att_length = ByteInfo.readInt(2);
                this.parameter_annotations.add(new Annotation_attributes(name_index, att_length));
            }
        }

    }
}
