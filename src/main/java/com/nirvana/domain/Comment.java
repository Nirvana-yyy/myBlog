package com.nirvana.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author YJL
 */
@Data

public class Comment {
    private  Integer commentId;

    /**
     * 评论发布的时间
     */
    private Date commentTime;

    /**
     * 发布评论的用户的id
     */
    private Integer commentUserId;

    /**
     * 评论所属的文章的id
     */
    private Integer commentBlogId;

    /**
     * 子评论的id
     */
    private Integer commentLastId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 数据库字段中没有
     * 上一级回复的nickname
     */
    private String commentLastNickname;
}
