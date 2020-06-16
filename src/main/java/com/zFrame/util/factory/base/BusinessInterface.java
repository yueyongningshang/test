package com.zFrame.util.factory.base;

import java.util.Map;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：BusinessInterface    
 * 类描述：业务接口    
 * 创建人：Gz    
 * 创建时间：2019年3月22日 上午10:14:33    
 * 修改人：Gz    
 * 修改时间：2019年3月22日 上午10:14:33    
 * 修改备注：    
 * @version     
 *
 */
public interface BusinessInterface {

    /**
     * 
     * businessHandle(业务接口)    
     * @param   name    
     * @param  @return    设定文件    
     * @return String    DOM对象    
     * @Exception 异常对象
     */
    Map<String, Object> businessHandle(Map<String, Object> param);

}
