package com.dygstudio.epsms.service.controller;

import com.dygstudio.epsms.service.common.RocketMQUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈功能概述〉
 *
 * @className: TestController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2020/7/21 16:21
 */
@Controller
@RequestMapping("/api/test")
public class TestController extends BaseController {
    @ResponseBody
    @RequestMapping(value = "sendRocketMQMessage")
    public String testSend(){
        String message = "测试数据 ";
        try {
            RocketMQUtils.sendMessage(message);
            return "发送成功";
        }catch (Exception ex){
            ex.printStackTrace();
            return "发送失败"+ex.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "getRocketMQMessage")
    public String testGet(){
        try {
            RocketMQUtils.getMessage();
            return "获取成功";
        }catch (Exception ex){
            ex.printStackTrace();
            return "获取失败"+ex.getMessage();
        }
    }
}
