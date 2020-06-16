package com.zFrame.util.factory.execute;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zFrame.util.factory.base.BusinessInterface;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：AplanBusiness    
 * 类描述：A计划处理业务    
 * 创建人：Gz    
 * 创建时间：2019年3月22日 上午10:19:47    
 * 修改人：Gz    
 * 修改时间：2019年3月22日 上午10:19:47    
 * 修改备注：    
 * @version     
 *
 */
@Service
public class AplanBusiness implements BusinessInterface {

    @Override
    public Map<String, Object> businessHandle(Map<String, Object> param) {
        Map<String, Object> x = new HashMap<String, Object>();
        x.put("A", "A任务处理完成");
        return x;
    }

}
