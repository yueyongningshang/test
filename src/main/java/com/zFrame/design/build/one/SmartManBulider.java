package com.zFrame.design.build.one;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：SmartManBulider    
 * 类描述：ConcreteBuilder（具体建造者）    
 * 创建人：Gz    
 * 创建时间：2019年7月15日 上午11:20:56    
 * 修改人：Gz    
 * 修改时间：2019年7月15日 上午11:20:56    
 * 修改备注：    
 * @version     
 *
 */
public class SmartManBulider implements IBuildHuman {

    Human human;

    public SmartManBulider() {
        human = new Human();
    }

    @Override
    public void buildHead() {
        human.setHead("智商180的头脑");
    }

    @Override
    public void buildBody() {
        human.setBody("新的身体");
    }

    @Override
    public void buildHand() {
        human.setHand("新的手");
    }

    @Override
    public void buildFoot() {
        human.setFoot("新的脚");
    }

    @Override
    public Human createHuman() {
        return human;
    }

}
