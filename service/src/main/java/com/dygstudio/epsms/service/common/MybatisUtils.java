package com.dygstudio.epsms.service.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * 〈功能概述〉这个代码是用在 Spring 项目中的，SpringBoot 该工具类暂时可不使用，Springboot框架 自动引入SqlSession 等对象
 *
 * @className: MybatisUtils
 * @package: com.dygstudio.epsms.service.common
 * @author: diyaguang
 * @date: 2019-12-30 15:54
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        try{
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
