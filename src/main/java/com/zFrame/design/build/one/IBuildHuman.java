package com.zFrame.design.build.one;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：IBuildHuman    
 * 类描述：Builder（抽象建造者）    
 * 创建人：Gz    
 * 创建时间：2019年7月15日 上午11:20:18    
 * 修改人：Gz    
 * 修改时间：2019年7月15日 上午11:20:18    
 * 修改备注：    
 * @version     
 *
 */
public interface IBuildHuman {

    void buildHead();

    void buildBody();

    void buildHand();

    void buildFoot();

    Human createHuman();

}
