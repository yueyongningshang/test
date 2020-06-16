package com.zFrame.util.factory;

import com.zFrame.util.factory.base.BusinessInterface;
import com.zFrame.util.factory.execute.AplanBusiness;
import com.zFrame.util.factory.execute.BplanBusiness;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestFactory    
 * 类描述：工厂类    
 * 创建人：Gz    
 * 创建时间：2019年3月22日 上午10:25:08    
 * 修改人：Gz    
 * 修改时间：2019年3月22日 上午10:25:08    
 * 修改备注：    
 * @version     
 *
 */
public class TestFactory {

    /**
     * 
     * handleBusiness(工厂类处理选择业务)    
     * @param   name    
     * @param  @return    设定文件    
     * @return String    DOM对象    
     * @Exception 异常对象
     */
    public static BusinessInterface handleBusiness(String type) {
        if ("A".equals(type)) {
            return new AplanBusiness();
        }
        if ("B".equals(type)) {
            return new BplanBusiness();
        }
        return null;
    }
}
