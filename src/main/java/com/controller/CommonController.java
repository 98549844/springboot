package com.controller;

import org.springframework.web.servlet.ModelAndView;

public class CommonController {

    /**
     * 返回页面
     *
     * @param page
     * @return
     */
    protected ModelAndView page(String page) {
        ModelAndView mv = new ModelAndView(page);
        return mv;
    }
}
