package com.nirvana.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YJL
 */
@Controller
public class ViewTest1 {

    @RequestMapping("/header")
    public String test1(){
        return "home/public/part/header";
    }
}
