package com.nirvana.service;

import com.github.pagehelper.PageInfo;
import com.nirvana.domain.Article;

import java.io.IOException;
import java.util.List;

/**
 * @author YJL
 */
public interface ArticleService {

    /**
     * 查询所有的文章信息
     * @return
     */
    public PageInfo<Article> articlePagingList(Integer pageIndex, Integer pageSize) throws IOException;

    List<Integer> findLikeCountAndArticleCount(Integer userId) throws IOException;

    /**
     * 通过id来查找文章
     * @param id
     * @return
     */
    public Article findArticleById(int id) throws IOException;


    /**
     * 查询该用户的所有文章
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Article> findAllArticlesByUserId(Integer userId, Integer pageIndex, Integer pageSize) throws IOException;

    /**
     * 添加新文章
     * @param article
     * @return
     */
    Boolean addArticle(Article article) throws IOException;

    PageInfo<Article> articlePagingListTypeOne(Integer pageIndex, Integer pageSize) throws IOException;

    PageInfo<Article> articlePagingListTypeTwo(Integer pageIndex, Integer pageSize) throws IOException;

    PageInfo<Article> articlePagingListTypeThree(Integer pageIndex, Integer pageSize) throws IOException;

    PageInfo<Article> articlePagingListTypeFour(Integer pageIndex, Integer pageSize) throws IOException;
}
