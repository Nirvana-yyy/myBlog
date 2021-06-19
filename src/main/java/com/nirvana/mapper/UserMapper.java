package com.nirvana.mapper;

import com.nirvana.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YJL
 */
public interface UserMapper {

    /**
     * 通过id查找user
     * @param articleUserId
     * @return
     */
    public User getUserById(Integer articleUserId);

    /**
     * 判断是否存在用户名为username的用户
     * @param username
     * @return
     */
    public User usernameExist(String username);

    /**
     * 通过username和password来查找出user
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 添加新用户
     * @param user
     * @return
     */
    Boolean addUser(User user);

    int getUserId(Integer commentId);
}
