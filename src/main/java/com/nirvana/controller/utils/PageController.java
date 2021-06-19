package com.nirvana.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YJL
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/login")
    public String jumpToLoginPage(){
        return "/admin/login";
    }

    @RequestMapping("/writeArticle")
    public String jumpToWriteArticle(){
        return "/home/public/part/writingSpace";
    }

    @RequestMapping("/register")
    public String jumpToRegister(){

        return "/admin/register";
    }
    @RequestMapping("/userArticleList")
    public String jumpToArticleList(){
        return "/home/public/part/userArticleList";
    }
    @RequestMapping("/error")
    public String jumToError(){
        return "/home/erro/error";
    }
    @RequestMapping("/show")
    public void show(){
        int i = 1/0;
    }
}
