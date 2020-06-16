// package com.zFrame.control.activeMq.demo2;
//
// import java.util.List;
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
// * 类名称：CustomerQueueItem
// * 类描述： 货代业务归集后申请单分录审批后调用接口自动拆分成本 -- 分录
// * 创建人：wuwenjin
// * 创建时间：2020年4月24日 下午3:16:03
// * 修改人：wuwenjin
// * 修改时间：2020年4月24日 下午3:16:03
// * 修改备注：
// * @version 1.0
// *
// */
// @Slf4j
// @Component
// public class CustomerQueueItem {
// // queue模式的消费者ok
// // @JmsListener(destination = "cost_listen_item", containerFactory =
// // "queueListener")
// public void readActiveQueue(@RequestBody List<Student> params) {
// System.out.println("执行service业务逻辑----readActiveQueue---" +
// JSONObject.toJSONString(params));
// }
// }
