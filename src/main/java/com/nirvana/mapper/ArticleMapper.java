package com.nirvana.mapper;

import com.nirvana.domain.Article;
import org.aspectj.weaver.reflect.InternalUseOnlyPointcutParser;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author YJL
 */
public interface ArticleMapper {

    /**
     * 查询所有的文章信息
     * @return
     */
    public List<Article> findAll();

    /**
     * 根据用户id来查找用户文章总数和获赞总数
     * @param userId
     * @return
     */
    List<Integer> findLikeCountAndArticleCount(Integer userId);

    /**
     * 根据用户id查找用户文章总数
     * @param userId
     * @return
     */
    Integer findArticleCount(Integer userId);



    /**
     * 查找出所有的文章通过用户的id
     * @param userId
     * @return
     */
    List<Article> findAllArticlesByUserId(Integer userId);

    /**
     * 通过id查找出文章
     * @param id
     * @return
     */
    Article findArticleById(Integer id);

    /**
     * 添加文章
     * @param article
     * @return
     */
    Boolean addArticle(Article article);

    /**
     * 找到同类型的文章
     * @param i
     * @return
     */
    List<Article> findArticleByType(int i);

    /**
     * 删除文章byID
     * @param userId
     * @return
     */
    Boolean deleteArticleById(Integer userId);
}
