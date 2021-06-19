package com.nirvana.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author YJL
 */
@Data
public class User {
    Integer userId;

    String username;

    String password;
    /**
     * 用户昵称
     */
    String userNickname;
    /**
     * 用户头像
     */
    String userAvatar;
    /**
     * 最后登录时间
     */
    Date userLastTime;

    /**
     * 数据库中user表里没有
     * 文章总数
     */
    Integer articleCount;

    /**
     * 数据库中user表里没有
     * 获赞总数
     */
    Integer likeCount;
}
