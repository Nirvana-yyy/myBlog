package com.nirvana.service;

import com.nirvana.domain.Comment;
import com.nirvana.domain.extend.CommentNode;

import java.io.IOException;
import java.util.List;

/**
 * @author YJL
 */
public interface CommentService {

    /**
     * 根据博客id查询此博客的所有的评论信息
     * @param blogId
     * @return
     */
    List<CommentNode> queryCommentByBlogId(int blogId) throws IOException;

    /**
     * 根据评论id查询用户信息
     * @param commentId
     * @return
     */
    CommentNode queryCommentUserById(Integer commentId, CommentNode commentNode) throws IOException;

    /**
     * 给指定文章添加评论
     * @param comment
     * @return
     */
    Boolean addArticleComments(Comment comment) throws IOException;

    /**
     * 查找出所有的评论集合(前十条按照时间顺序)
     * @return
     */
    List<Comment> findAll() throws IOException;

}
