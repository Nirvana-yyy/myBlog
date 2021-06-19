package com.nirvana.service.impl;

import com.nirvana.domain.User;
import com.nirvana.mapper.ArticleMapper;
import com.nirvana.mapper.UserMapper;
import com.nirvana.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author YJL
 */
@Service("UserService")
public class UserImpl implements UserService {



    @Override
    public boolean usernameExist(String username) throws IOException {
        InputStream resourceAsStream = null;
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.usernameExist(username);
            if (user == null) {
                return false;
            }else{
                return true;
            }

    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findUserByUsernameAndPassword(username,password);
            return user;

    }

    @Override
    public User findUserById(Integer articleUserId) throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(articleUserId);
            return user;
    }

    @Override
    public Boolean addUser(User user) throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
           Boolean bool = userMapper.addUser(user);

            return bool;


    }

    @Override
    public Boolean deleteArticleById(Integer userId) throws IOException {
        InputStream resourceAsStream = null;

        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        Boolean bool = articleMapper.deleteArticleById(userId);

        return bool;
    }
}
