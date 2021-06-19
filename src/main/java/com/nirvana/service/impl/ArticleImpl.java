package com.nirvana.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nirvana.domain.Article;
import com.nirvana.mapper.ArticleMapper;
import com.nirvana.mapper.UserMapper;
import com.nirvana.service.ArticleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YJL
 */
@Service("ArticleService")
public class ArticleImpl implements ArticleService {



    @Override
    public PageInfo<Article> articlePagingList(Integer pageIndex, Integer pageSize) throws IOException {

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            //设置分页相关的参数，当前页+每页显示的条数
            //注意这里有一个小bug：必须是先设置分页参数，再进行查询，不然会导致PageInfo值为空
            PageHelper.startPage(pageIndex, pageSize);

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Article> articleList = articleMapper.findAll();

            for (int i = 0; i < articleList.size(); i++) {
                //查找文章作者并设置
                articleList.get(i).setUser(userMapper.getUserById(articleList.get(i).getArticleUserId()));
            }


            return new PageInfo<>(articleList);

    }

    @Override
    public List<Integer> findLikeCountAndArticleCount(Integer userId) throws IOException {

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            //查找出用户的文章总数
            Integer articleCount = articleMapper.findArticleCount(userId);
            //查找出用户的文章
            List<Article> userArticle = articleMapper.findAllArticlesByUserId(userId);
            //遍历文章计算得出 用户获赞总数
            int likeCount = 0;
            for (Article article : userArticle) {
                likeCount += article.getArticleLikeCount();
            }
            //返回一个集合
            List<Integer> likeCountAndArticleCount = new ArrayList<>();
            likeCountAndArticleCount.add(0,likeCount);
            likeCountAndArticleCount.add(1,articleCount);
            return likeCountAndArticleCount;
    }

    @Override
    public Article findArticleById(int id) throws IOException {

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            Article article = articleMapper.findArticleById(id);
            return article;

    }

    @Override
    public PageInfo<Article> findAllArticlesByUserId(Integer userId, Integer pageIndex, Integer pageSize) throws IOException {

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            //设置分页相关的参数，当前页+每页显示的条数
            //注意这里有一个小bug：必须是先设置分页参数，再进行查询，不然会导致PageInfo值为空
            PageHelper.startPage(pageIndex, pageSize);
            List<Article> articles = articleMapper.findAllArticlesByUserId(userId);
            return new PageInfo<>(articles);

    }

    @Override
    public Boolean addArticle(Article article) throws IOException {

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            Boolean bool = articleMapper.addArticle(article);


            return bool;

    }

    @Override
    public PageInfo<Article> articlePagingListTypeOne(Integer pageIndex, Integer pageSize) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        //设置分页相关的参数，当前页+每页显示的条数
        //注意这里有一个小bug：必须是先设置分页参数，再进行查询，不然会导致PageInfo值为空
        PageHelper.startPage(pageIndex, pageSize);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Article> articleList = articleMapper.findArticleByType(1);

        for (int i = 0; i < articleList.size(); i++) {
            //查找文章作者并设置
            articleList.get(i).setUser(userMapper.getUserById(articleList.get(i).getArticleUserId()));
        }


        return new PageInfo<>(articleList);
    }

    @Override
    public PageInfo<Article> articlePagingListTypeTwo(Integer pageIndex, Integer pageSize) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        //设置分页相关的参数，当前页+每页显示的条数
        //注意这里有一个小bug：必须是先设置分页参数，再进行查询，不然会导致PageInfo值为空
        PageHelper.startPage(pageIndex, pageSize);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Article> articleList = articleMapper.findArticleByType(2);

        for (int i = 0; i < articleList.size(); i++) {
            //查找文章作者并设置
            articleList.get(i).setUser(userMapper.getUserById(articleList.get(i).getArticleUserId()));
        }


        return new PageInfo<>(articleList);
    }

    @Override
    public PageInfo<Article> articlePagingListTypeThree(Integer pageIndex, Integer pageSize) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        //设置分页相关的参数，当前页+每页显示的条数
        //注意这里有一个小bug：必须是先设置分页参数，再进行查询，不然会导致PageInfo值为空
        PageHelper.startPage(pageIndex, pageSize);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Article> articleList = articleMapper.findArticleByType(3);

        for (int i = 0; i < articleList.size(); i++) {
            //查找文章作者并设置
            articleList.get(i).setUser(userMapper.getUserById(articleList.get(i).getArticleUserId()));
        }


        return new PageInfo<>(articleList);
    }

    @Override
    public PageInfo<Article> articlePagingListTypeFour(Integer pageIndex, Integer pageSize) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        //设置分页相关的参数，当前页+每页显示的条数
        //注意这里有一个小bug：必须是先设置分页参数，再进行查询，不然会导致PageInfo值为空
        PageHelper.startPage(pageIndex, pageSize);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Article> articleList = articleMapper.findArticleByType(4);

        for (int i = 0; i < articleList.size(); i++) {
            //查找文章作者并设置
            articleList.get(i).setUser(userMapper.getUserById(articleList.get(i).getArticleUserId()));
        }


        return new PageInfo<>(articleList);
    }
}
