package com.nirvana.controller.home;

import com.nirvana.domain.Comment;
import com.nirvana.domain.User;
import com.nirvana.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author YJL
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment/{articleId}/{userId}")
    public String addArticleComments(@PathVariable("articleId")Integer articleId, Model model, HttpServletRequest request,@PathVariable("userId")Integer userId) throws IOException {

        //获取评论内容
        String addComment = request.getParameter("addComment");

        //获取lastId
        String lastIdStr = request.getParameter("lastId");
        Integer lastId;
        //防止空指针异常
        if (lastIdStr == null){
            lastId = null;
        }else{
            lastId = Integer.parseInt(lastIdStr);
        }

        //获取时间日期
        Date date = new Date();
        //装配comment
        Comment comment = new Comment();
        comment.setCommentBlogId(articleId);
        comment.setCommentContent(addComment);
        comment.setCommentLastId(lastId);
        comment.setCommentUserId(userId);
        comment.setCommentTime(date);
        Boolean aBoolean = commentService.addArticleComments(comment);

        if (aBoolean){

            return "redirect:/article/{articleId}";
        }else{
            return "/home/erro/error";
        }

    }

}
