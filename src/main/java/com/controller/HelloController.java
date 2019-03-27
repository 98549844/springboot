package com.controller;

import com.entity.TestEntity;
import com.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController extends CommonController {
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public String index() {
        log.info("CONTROLLER***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        TestEntity test = new TestEntity();
        test.setUserId(100);
        test.setUserName("peter");
        test.setEmail("peter.li@sms.com");

        testService.saveTestEntity(test);
        return "hello spring boot!!!";
    }


    /**
     * @return 返回空白页
     */
    @RequestMapping("/blank.html")
    public ModelAndView blank() {
        //返回空白页未处理
        log.info("CONTROLLER***" + this.getClass().getName());


        ModelAndView mv = super.page("blank.jsp");
        return mv;
    }


}