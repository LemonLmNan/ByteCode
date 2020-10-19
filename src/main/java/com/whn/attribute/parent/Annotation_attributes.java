package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;
import com.whn.attribute.child.AnnotationAttribute;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 01:29
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class Annotation_attributes extends MyAttribute{

    private int num_annotations;
    private List<AnnotationAttribute> annotations;

    public Annotation_attributes(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        int num_annotations = ByteInfo.readInt(2);
        this.num_annotations = num_annotations;

        if(num_annotations > 0){
            this.annotations = Utils.newArrayList(num_annotations << 1);
            for (int num = 0; num < num_annotations; num++){
                this.annotations.add(new AnnotationAttribute());
            }
        }
    }
}
