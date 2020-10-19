package com.whn.attribute.parent;

import com.whn.attribute.MyAttribute;
import com.whn.attribute.child.Bootstrap_method;
import com.whn.common.ByteInfo;
import com.whn.common.Utils;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-19
 * Time: 20:18
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class BootstrapMethods_attribute extends MyAttribute{

    private int num_bootstrap_methods;
    private List<Bootstrap_method> bootstrap_methods;

    public BootstrapMethods_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        int num_bootstrap_methods = ByteInfo.readInt(2);

        if(num_bootstrap_methods > 0){
            List<Bootstrap_method> bootstrap_methods = Utils.newArrayList(num_bootstrap_methods << 1);
            for(int num = 0; num < num_bootstrap_methods; num++){
                bootstrap_methods.add(new Bootstrap_method());
            }
            this.bootstrap_methods = bootstrap_methods;
        }
    }
}
