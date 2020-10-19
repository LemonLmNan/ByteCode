package com.whn.attribute.child;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-19
 * Time: 20:20
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class Bootstrap_method{

    private int bootstrap_method_ref;
    private int num_bootstrap_arguments;
    private List<Integer> bootstrap_arguments;

    public Bootstrap_method()throws Throwable{
        this.bootstrap_method_ref = ByteInfo.readInt(2);
        int num_bootstrap_arguments = ByteInfo.readInt(2);
        this.num_bootstrap_arguments = num_bootstrap_arguments;

        if(num_bootstrap_arguments > 0){
            List<Integer> bootstrap_arguments = Utils.newArrayList(num_bootstrap_arguments << 1);
            // 里面的每一个都是常量池中的下标
            for(int num = 0; num < num_bootstrap_arguments; num++){
                bootstrap_arguments.add(ByteInfo.readInt(2));
            }
            this.bootstrap_arguments = bootstrap_arguments;
        }
    }
}
