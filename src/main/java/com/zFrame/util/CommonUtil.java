package com.zFrame.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CommonUtil {

    /**
     * @version 1.0
     * @methodname isEmpty
     * @description 判断传入数据是否为空
     * @param Object
     * @return boolean 
     * @author wuwenjin
     * @date 2018年4月25日-下午4:42:37
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o.equals("null")) {
            return true;
        }
        if (o instanceof String) {
            if (((String) o).trim().length() == 0) {
                return true;
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).isEmpty()) {
                return true;
            }

        } else if (o.getClass().isArray()) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
