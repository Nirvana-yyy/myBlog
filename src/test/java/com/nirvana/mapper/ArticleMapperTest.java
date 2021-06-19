package com.nirvana.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nirvana.domain.Article;
import com.nirvana.service.ArticleService;
import com.nirvana.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ArticleMapperTest extends BaseTest{

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Test
    public void articlePagingList() throws IOException {
        PageInfo<Article> pageInfo = articleService.articlePagingList(1, 5);
        List<Article> articleList = pageInfo.getList();
        for (Article article : articleList) {
            System.out.println(article);
        }
        System.out.println(pageInfo);
    }
    @Test
    public void test2() throws IOException {
        List<Integer> likeCountAndArticleCount = articleService.findLikeCountAndArticleCount(1);
        for (Integer integer : likeCountAndArticleCount) {
            System.out.println("文章总数"+integer);

        }
    }

    @Test
    public void Test3() throws IOException {
        Article article = articleService.findArticleById(1);
        System.out.println(article);
    }
    @Test
    public void test4() throws IOException {
        PageInfo<Article> articles = articleService.findAllArticlesByUserId(1, 1, 8);
        List<Article> list = articles.getList();
        for (Article article : list) {
            System.out.println(article);
        }
    }
    @Test
    public void Test5() throws IOException {
        Article article = new Article();
        article.setArticleContent("1");
        article.setArticleCommentCount(1);
        article.setArticleTitle("2");
        article.setArticleUserId(1);
        article.setArticleType(1);
        article.setArticleCreateTime(new Date());
        article.setArticleSummary("3");
        article.setArticleLikeCount(0);
        article.setArticleRandomPic("5");
        Boolean aBoolean = articleService.addArticle(article);
        System.out.println(aBoolean);
    }

    @Test
    public void test6() throws IOException {
        PageInfo<Article> pageInfo = articleService.articlePagingListTypeOne(1, 8);
        List<Article> list = pageInfo.getList();
        for (Article article : list) {
            System.out.println(article);
        }
    }

    @Test
    public void test7() throws IOException {
        Boolean aBoolean = userService.deleteArticleById(24);
        System.out.println(aBoolean);
    }

}