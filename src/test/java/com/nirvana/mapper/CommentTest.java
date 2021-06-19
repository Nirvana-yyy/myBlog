package com.nirvana.mapper;

import com.nirvana.domain.Comment;
import com.nirvana.domain.extend.CommentNode;
import com.nirvana.service.CommentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class CommentTest extends BaseTest{
    @Autowired
    CommentService commentService;

    @Test
    public void test1() throws IOException {
        List<CommentNode> firstCommentNode = commentService.queryCommentByBlogId(4);
        for (CommentNode commentNode : firstCommentNode) {
            System.out.println(commentNode);
        }

    }

    @Test
    public void test2() throws IOException {
        List<Comment> all = commentService.findAll();
        int i = 0;
        for (Comment comment : all) {
            i++;
            System.out.println(comment);
            System.out.println(i);
        }
    }
}
