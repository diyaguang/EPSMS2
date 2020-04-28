package com.dygstudio.epsms.service.common;

import java.util.UUID;

/**
 * 〈功能概述〉
 *
 * @className: CommonUtils
 * @package: com.dygstudio.epsms.service.common
 * @author: diyaguang
 * @date: 2020/4/27 16:06
 */
public class CommonUtils {
    public static String GenerateId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
