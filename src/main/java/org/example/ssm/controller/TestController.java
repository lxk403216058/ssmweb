package org.example.ssm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api(value = "Test")
public class TestController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value = "zheshiyige")
    public String index(){
        System.out.println("测试....");
        return "test";
    }
}
