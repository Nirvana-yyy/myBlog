package com.nirvana.service;

import com.nirvana.domain.User;

import java.io.IOException;

/**
 * @author YJL
 */
public interface UserService {

    /**
     * 判断给定的用户名是否存在
     * @param username
     * @return
     */
    public boolean usernameExist(String username) throws IOException;

    /**
     * 通过username和password来查找user
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password) throws IOException;

    /**
     * 通过id查找出用户
     * @param articleUserId
     */
    User findUserById(Integer articleUserId) throws IOException;

    /**
     * 添加新用户
     * @param user
     * @return
     */
    Boolean addUser(User user) throws IOException;

    /**
     * 删除文章通过id
     * @param userId
     * @return
     */
    Boolean deleteArticleById(Integer userId) throws IOException;
}
