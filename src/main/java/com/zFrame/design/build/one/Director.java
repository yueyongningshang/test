package com.zFrame.design.build.one;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：Director    
 * 类描述： Director（指挥者）
 * 创建人：Gz    
 * 创建时间：2019年7月15日 上午11:17:48    
 * 修改人：Gz    
 * 修改时间：2019年7月15日 上午11:17:48    
 * 修改备注：    
 * @version     
 *
 */
public class Director {

    public Human createHumanByDirector(IBuildHuman bh) {
        bh.buildBody();
        bh.buildFoot();
        bh.buildHand();
        bh.buildHead();
        return bh.createHuman();
    }

}
