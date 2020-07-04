package com.dygstudio.epsms.service.controller;

import graphql.GraphQL;
import kotlin.jvm.internal.MagicApiIntrinsics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 〈功能概述〉
 *
 * @className: GraphQLController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2020/7/4 11:04
 */
@Controller
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @GetMapping("/graphql_controller")
    @ResponseBody
    public Map<String,Object> graphql(@RequestParam("query") String query){
        return graphQL.execute(query).toSpecification();
    }
}
