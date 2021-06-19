package com.nirvana.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author YJL
 */
@Data
public class Article {

    private Integer articleId;
    /**
     * 文章作者id
     */
    private Integer articleUserId;

    private String articleTitle;
    /**
     * 文章内容
     */
    private String articleContent;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Date articleCreateTime;
    /**
     * 文章图片
     */
    private String articleRandomPic;

    private Integer articleType;

    /**
     * 文章概要
     */
    private String articleSummary;

    /**
     * 数据库字段没有的
     */
    private User user;




}
