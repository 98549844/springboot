package com.controller;

import com.entity.TestEntity;
import com.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;

@RestController
public class TestController {
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String index() {

        log.info("CONTROLLER***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        TestEntity test = testService.getTestEntityById(1);
        System.out.println("print result***" + test.getUserName());

        return "hello spring boot!!!";
    }


}
