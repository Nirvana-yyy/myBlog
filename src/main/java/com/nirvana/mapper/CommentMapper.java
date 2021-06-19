package com.nirvana.mapper;

import com.mchange.v2.codegen.bean.PropsToStringGeneratorExtension;
import com.nirvana.domain.Comment;
import com.nirvana.domain.extend.CommentNode;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author YJL
 */
public interface CommentMapper {

    /**
     * 当lastId为空时，查询所有的一级评论
     * @param blogId
     * @return
     */
    List<CommentNode> queryFirstCommentList(int blogId);

    /**
     * 当lastId非空时，查询所有的二级评论
     * @param blogId
     * @return
     */
    List<CommentNode> querySecondCommentList(int blogId);

    /**
     * 根据id查找评论信息
     * @param commentId
     * @return
     */
    Comment selectByPrimaryKey(int commentId);

    /**
     * 添加评论数据
     * @param comment
     * @return
     */
    Boolean insertComment(Comment comment);

    /**
     * 查找出所有的评论集合(前十按照时间顺序)
     * @return
     */
    List<Comment> selectAll();


}
