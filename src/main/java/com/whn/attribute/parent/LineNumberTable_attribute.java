package com.whn.attribute.parent;

import com.whn.common.ByteInfo;
import com.whn.common.Utils;
import com.whn.attribute.MyAttribute;
import com.whn.attribute.child.Line_number_table;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-16
 * Time: 19:08
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public class LineNumberTable_attribute extends MyAttribute{

    private int line_number_table_length;
    private List<Line_number_table> line_number_table;

    public LineNumberTable_attribute(int attribute_name_index, long attribute_length)throws Throwable{
        super(attribute_name_index, attribute_length);
        int line_number_table_length = ByteInfo.readInt(2);
        this.line_number_table_length = line_number_table_length;

        if(line_number_table_length > 0){
            List number_table = Utils.newArrayList(line_number_table_length);
            for(int num = 0; num < line_number_table_length; num++){
                number_table.add(new Line_number_table());
            }
            this.line_number_table = number_table;
        }
    }
}
