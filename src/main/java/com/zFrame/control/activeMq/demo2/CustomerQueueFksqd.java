// package com.zFrame.control.activeMq.demo2;
//
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.RequestBody;
//
// import com.alibaba.fastjson.JSONObject;
// import com.zFrame.entity.Student;
//
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * 项目名称：ff-settlement
// * 类名称：CustomerQueueFksqd
// * 类描述： 归集后的申请单进行成本分摊
// * 创建人：wuwenjin
// * 创建时间：2020年4月26日 上午11:27:44
// * 修改人：wuwenjin
// * 修改时间：2020年4月26日 上午11:27:44
// * 修改备注：
// * @version
// *
// */
// @Slf4j
// @Component
// public class CustomerQueueFksqd {
// // queue模式的消费者
// // @JmsListener(destination = "cost_listen", containerFactory =
// // "queueListener")
// public void readActiveQueue(@RequestBody Student params) {
// System.out.println("执行service业务逻辑----readActiveQueue---" +
// JSONObject.toJSONString(params));
// }
// }
