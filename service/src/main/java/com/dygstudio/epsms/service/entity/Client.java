package com.dygstudio.epsms.service.entity;

/**
 * 〈功能概述〉
 *
 * @className: Client
 * @package: com.dygstudio.epsms.service.entity
 * @author: diyaguang
 * @date: 2020/7/4 11:31
 */
public class Client {
    private String id;
    private String name;
    private Integer age;

    public Client(String id,String name,Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
