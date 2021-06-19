package com.nirvana.controller.home;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nirvana.domain.Article;
import com.nirvana.domain.Comment;
import com.nirvana.domain.ResultInfo;
import com.nirvana.domain.User;
import com.nirvana.domain.extend.CommentNode;
import com.nirvana.service.ArticleService;
import com.nirvana.service.CommentService;
import com.nirvana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author YJL
 * @date 2021/5/29
 */
@Controller
//@RequestMapping("/home")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    //查询文章以及分页信息
    @RequestMapping(value = {"/","/index"})
    public ModelAndView index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false,defaultValue = "8") Integer pageSize, HttpServletRequest request) throws IOException {

        PageInfo<Article>  pageInfo = articleService.articlePagingList(pageIndex,pageSize);

        List<Comment> comments = commentService.findAll();

        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        //文章和分页相关参数集合
        modelAndView.addObject("pageInfo",pageInfo);
        //评论集合
        /*modelAndView.addObject("comments",comments);*/
        session.setAttribute("comments",comments);
        //跳转路径
        modelAndView.addObject("pageUrlPrefix","/index?pageIndex");
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    //分类查询文章以及分页信息
    @RequestMapping(value = "/type/1")
    public ModelAndView typeOne(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false,defaultValue = "8") Integer pageSize) throws IOException {

        PageInfo<Article>  pageInfo = articleService.articlePagingListTypeOne(pageIndex,pageSize);


        ModelAndView modelAndView = new ModelAndView();
        //文章和分页相关参数集合
        modelAndView.addObject("pageInfo",pageInfo);
        //评论集合
        /*modelAndView.addObject("comments",comments);*/

        //跳转路径
        modelAndView.addObject("pageUrlPrefix","/type/1?pageIndex");
        modelAndView.setViewName("home/index");
        return modelAndView;
    }


    //分类查询文章以及分页信息
    @RequestMapping(value = "/type/2")
    public ModelAndView typeTwo(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                @RequestParam(required = false,defaultValue = "8") Integer pageSize) throws IOException {

        PageInfo<Article>  pageInfo = articleService.articlePagingListTypeTwo(pageIndex,pageSize);


        ModelAndView modelAndView = new ModelAndView();
        //文章和分页相关参数集合
        modelAndView.addObject("pageInfo",pageInfo);
        //评论集合
        /*modelAndView.addObject("comments",comments);*/

        //跳转路径
        modelAndView.addObject("pageUrlPrefix","/type/2?pageIndex");
        modelAndView.setViewName("home/index");
        return modelAndView;
    }


    //分类查询文章以及分页信息
    @RequestMapping(value = "/type/3")
    public ModelAndView typeThree(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                @RequestParam(required = false,defaultValue = "8") Integer pageSize) throws IOException {

        PageInfo<Article>  pageInfo = articleService.articlePagingListTypeThree(pageIndex,pageSize);


        ModelAndView modelAndView = new ModelAndView();
        //文章和分页相关参数集合
        modelAndView.addObject("pageInfo",pageInfo);
        //评论集合
        /*modelAndView.addObject("comments",comments);*/

        //跳转路径
        modelAndView.addObject("pageUrlPrefix","/type/3?pageIndex");
        modelAndView.setViewName("home/index");
        return modelAndView;
    }


    //分类查询文章以及分页信息
    @RequestMapping(value = "/type/4")
    public ModelAndView typeFour(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                @RequestParam(required = false,defaultValue = "8") Integer pageSize) throws IOException {

        PageInfo<Article>  pageInfo = articleService.articlePagingListTypeFour(pageIndex,pageSize);


        ModelAndView modelAndView = new ModelAndView();
        //文章和分页相关参数集合
        modelAndView.addObject("pageInfo",pageInfo);
        //评论集合
        /*modelAndView.addObject("comments",comments);*/

        //跳转路径
        modelAndView.addObject("pageUrlPrefix","/type/4?pageIndex");
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    /**
     * 展示文章数据
     * @param articleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable("articleId")Integer articleId , Model model) throws IOException {
        //查找出文章信息
        Article article = articleService.findArticleById(articleId);

        if (article == null){
            return "/home/erro/error";
        }
        //获取用户信息
        User user = userService.findUserById(article.getArticleUserId());
        article.setUser(user);

        //文章信息
        model.addAttribute("article", article);

        //评论列表
        List<CommentNode> commentNodes = commentService.queryCommentByBlogId(article.getArticleId());


        model.addAttribute("commentNodes",commentNodes);

        return "home/public/part/articleDetails";
    }

    @RequestMapping(value = "/articleDel/{articleId}/{userId}")
    public String articleDel(@PathVariable("articleId")Integer articleId,@PathVariable("userId")Integer userId) throws IOException {
        Boolean bool = userService.deleteArticleById(articleId);
        if (!bool){
            return "/home/erro/error";
        }
        String s = String.valueOf(userId);
        return "redirect:/user/"+s;
    }

    @RequestMapping(value = "/writing/{userId}")
    @ResponseBody
    public void addArticleInUser(@RequestBody Map<String,Object> map, @PathVariable("userId")Integer userId, HttpServletResponse response) throws IOException {
        //获取文章类型
        Integer articleType = Integer.parseInt((String) map.get("articleType"));

        //获取文章内容
        String articleContent = (String) map.get("articleContent");
        //获取文章标题
        String articleTitle = (String) map.get("articleTitle");
        //获取文章摘要
        String articleSummary = (String) map.get("articleSummary");
        //新建文章获赞数置为0
        Integer articleLikeCount = 0;
        //新建文章评论数置为0
        Integer articleCommentCount = 0;
        //获取当前时间
        Date articleCreateTime = new Date();
        //获取一个[0，8)的随机数用来作为文章图片
        Random random = new Random();
        int articleRandomPicNum = random.nextInt(8);
        //拼接图片路径的字符串
        String articleRandomPic = "/img/articlePic/img"+articleRandomPicNum+".jpg";

        Article article = new Article();
        //装填article
        article.setArticleContent(articleContent);
        article.setArticleCommentCount(articleCommentCount);
        article.setArticleTitle(articleTitle);
        article.setArticleUserId(userId);
        article.setArticleType(articleType);
        article.setArticleCreateTime(articleCreateTime);
        article.setArticleSummary(articleSummary);
        article.setArticleLikeCount(articleLikeCount);
        article.setArticleRandomPic(articleRandomPic);

        ResultInfo info = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        //调用service方法
        Boolean bool = articleService.addArticle(article);
        if (!bool){
            info.setFlag(false);
            info.setErrorMsg("保存文章失败");
        }else{
            info.setFlag(true);
        }
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
