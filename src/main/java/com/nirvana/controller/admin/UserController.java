package com.nirvana.controller.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.nirvana.domain.Article;
import com.nirvana.domain.ResultInfo;
import com.nirvana.domain.User;
import com.nirvana.service.ArticleService;
import com.nirvana.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YJL
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/findUser")
    public void FindUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户名
        String username = request.getParameter("username");
        //设置返回的json数据格式
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        //调用service层来判断用户名是否存在
        if (userService.usernameExist(username)){
            //存在
            map.put("userExist",true);
            map.put("msg","用户名可用");
            map.put("msg_regist","此用户名已存在!");
        }else{
            //不存在
            map.put("userExist",false);
            map.put("msg","用户名不存在");
            map.put("msg_regist","用户名可用");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    @RequestMapping("/login")
    public String Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置统一的编码
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String checkCode = request.getParameter("checkCode");
        //获取验证码的session
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!(checkcode_server.equalsIgnoreCase(checkCode))){
            request.setAttribute("msg","验证码错误");
            return "/admin/login";
//            model.addObject("msg","验证码错误");
//            model.setViewName("/admin/login");

        }
        String username = request.getParameter("username");
        String password  = request.getParameter("password");

        User user =  userService.findUserByUsernameAndPassword(username,password);
        if (user == null){

            request.setAttribute("msg","密码错误");
//            model.addObject("msg","密码错误");
//            model.setViewName("/admin/login");
        }else{
            List<Integer> likeCountAndArticleCount = articleService.findLikeCountAndArticleCount(user.getUserId());
            Integer likeCount = likeCountAndArticleCount.get(0);
            Integer articleCount = likeCountAndArticleCount.get(1);
            user.setArticleCount(articleCount);
            user.setLikeCount(likeCount);
            session.setAttribute("user",user);
            return "redirect:/index";

        }
        return "/admin/login";
    }

    @RequestMapping({"/user/{userId}","/userArticleList"})
    public ModelAndView userCenter(@PathVariable("userId")Integer userId,@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                   @RequestParam(required = false,defaultValue = "8") Integer pageSize) throws IOException {
        //通过userid查找出该作者得所有文章
        PageInfo<Article> articlesPageInfo = articleService.findAllArticlesByUserId(userId,pageIndex,pageSize);

        ModelAndView modelAndView = new ModelAndView();
        //文章和分页相关参数集合
        modelAndView.addObject("articlesPageInfo",articlesPageInfo);
        //跳转路径
        modelAndView.addObject("pageUrlPrefix","/userArticleList?pageIndex");
        modelAndView.setViewName("/home/public/part/userArticleList");
        return modelAndView;
    }

    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取验证码
        String check = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //校验验证码
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误

            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info这个对象序列化为json并写回客户端
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);
            return;
        }
        String username = request.getParameter("username");
        String password  = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String avatar = request.getParameter("avatar");
        String userAvatar = "/img/avatar/avatar"+avatar+".jpg";
        Date date = new Date();

        User user = new User();
        user.setLikeCount(0);
        user.setUsername(username);
        user.setArticleCount(0);
        user.setPassword(password);
        user.setUserAvatar(userAvatar);
        user.setUserNickname(nickname);
        user.setUserLastTime(date);

        Boolean bool = userService.addUser(user);
        ResultInfo info = new ResultInfo();
        if(bool){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //将info这个对象序列化为json并写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
