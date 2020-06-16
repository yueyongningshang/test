package com.zFrame.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zFrame.entity.Test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：PostAndGetControl    
 * 类描述：Post Get接口    
 * 创建人：Gz    
 * 创建时间：2019年2月12日 下午5:52:42    
 * 修改人：Gz    
 * 修改时间：2019年2月12日 下午5:52:42    
 * 修改备注：    
 * @version     
 *
 */
@RestController
// 协议集描述 作用类
@Api(value = "测试接口")
public class PostAndGetControl {

    /**
     * 
     * testGet(get请求)    
     */
    // 协议描述 作用于方法
    @ApiOperation("根据用户id查询用户车板信息")
    // @ApiImplicitParams 非对象参数集 @ApiImplicitParam非对象参数
    // 如果有多个字段描述，需要加@ApiImplicitParams，单个参数可不加
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eno", value = "用户编号", paramType = "path", required = true, dataType = "String"),
            @ApiImplicitParam(name = "enoTwo", value = "用户编号2", paramType = "path", required = true, dataType = "String") })
    // @ApiImplicitParam(name="eno", value="用户编号", paramType="path",
    // required=true, dataType="String")
    // @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
    @GetMapping("/v1/userFlatcar/eno/{eno}/{enoTwo}")
    // @PathVariable绑定URI模板变量值 即占位符{eno}
    public String testGet(@PathVariable("eno") String eno, @PathVariable("enoTwo") String enoTwo) {
        String result = "失败";
        try {
            System.out.println("eno-------------********************---------------------" + eno);
            System.out.println("enoTwo-------------********************---------------------" + enoTwo);
            result = "成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * testGet2(get请求接收对象)
     * 使用@ModelAttribute定义，  
     */
    @GetMapping("/v1/test/test2")
    public String testGet2(@ModelAttribute Test test) {
        String result = "失败";
        try {
            System.out.println("eno-------------********************---------------------" + test.getName());
            result = "成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * testPost(post请求。接map，hearder数据)    
     * @param   name    
     * @param  @return    设定文件    
     * @return String    DOM对象    
     * @Exception 异常对象
     */
    // @RequestMapping(value = "/v1/heiJudy", method = RequestMethod.POST,
    // produces = "application/json;charset=UTF-8")
    @ApiOperation("测试header,body,url传参")
    @PostMapping("/v1/testPost/{eno}")
    // @RequestBody，必须是一个post请求，GET请求中不可以使用@RequestBody，json数据通过body传输进来
    // @Produces("application/json;charset=UTF-8")
    // eno通过url requestParam传过来
    public String testPost(@RequestBody Map<String, Object> maps, @PathVariable String eno,
            // 使用@RequestHeader注解获取请求头的相关信息
            @RequestHeader("ticket") String headers) {
        String result = "失败";
        try {
            String para1 = maps.get("eNo").toString();
            System.out.println("para1-------------********************---------------------" + para1);
            System.out.println("eno-------------********************---------------------" + eno);
            System.out.println("header-------------********************---------------------" + headers);
            result = "成功";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * uploadExcel(导入Excel)    
     * @param boardSearchParams
     * @Exception 异常对象    
     * @author wuwenjin  
     * @date 2019年2月18日10:14:46
     */
    @ApiOperation(value = "Excel上传", notes = "上传文件必须为Excel 2003 格式文件")
    @PostMapping("/v1/bankDataImport/uploadExcel")
    public String uploadExcel(
            @ApiParam(value = "上传的文件", required = true) @RequestParam(value = "file", required = true) MultipartFile file,
            @ApiParam(value = "银行类型", required = true) @RequestParam(value = "bankType", required = true) String bankType,
            @ApiParam(value = "银行账户ID", required = false) @RequestParam(value = "accountId", required = false) String accountId,
            HttpServletRequest request) {
        try {
            System.out.println("11111111111111");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "q";
    }
}
