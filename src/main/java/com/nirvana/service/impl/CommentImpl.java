package com.nirvana.service.impl;


import com.nirvana.domain.Comment;
import com.nirvana.domain.User;
import com.nirvana.domain.extend.CommentNode;
import com.nirvana.mapper.CommentMapper;
import com.nirvana.mapper.UserMapper;
import com.nirvana.service.CommentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author YJL
 */
@Service
public class CommentImpl implements CommentService {



    @Override
    public List<CommentNode> queryCommentByBlogId(int blogId) throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            //所有未处理过的一级评论集合
            List<CommentNode> firstCommentList =queryFirstCommentList(blogId);
            //所有未处理过的二级评论集合
            List<CommentNode> secondCommentList = querySecondCommentList(blogId);
            //将二级评论用链表的方式添加到一级评论
            List<CommentNode> list = addAllNode(firstCommentList,secondCommentList);
            //测试：控制台打印
            show(list);

            return list;

    }

    /**
     * 添加LastNickName
     * @param blogId
     * @return
     */
    public List<CommentNode> querySecondCommentList(int blogId) throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //所有未处理过的二级评论集合
            List<CommentNode> secondCommentList = commentMapper.querySecondCommentList(blogId);
            for (int i = 0;i < secondCommentList.size();i++) {
                CommentNode commentNode = secondCommentList.get(i);
                CommentNode commentNode1 = queryCommentUserById(commentNode.getCommentLastId(), commentNode);
                secondCommentList.remove(i);
                secondCommentList.add(i,commentNode1);
            }
            return secondCommentList;

    }

    /**
     * 添加LastNickName
     * @param blogId
     * @return
     */
    public List<CommentNode> queryFirstCommentList(int blogId) throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //所有未处理过的一级评论集合
            List<CommentNode> firstCommentList = commentMapper.queryFirstCommentList(blogId);

            return firstCommentList;

    }

    @Override
    public CommentNode queryCommentUserById(Integer commentId, CommentNode commentNode) throws IOException {
        if (commentNode == null){
            return commentNode;
        }
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            /*//通过主键查找出评论
            Comment comment = commentMapper.selectByPrimaryKey(commentId);*/
            //查找出评论对应的user
            int userId = userMapper.getUserId(commentId);
            User user = userMapper.getUserById(userId);
            commentNode.setCommentLastNickname(user.getUserNickname());
            return commentNode;
        }

    @Override
    public Boolean addArticleComments(Comment comment) throws IOException {


        InputStream resourceAsStream = null;

        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);

        Boolean bool = commentMapper.insertComment(comment);
        return bool;

    }
    @Override
    public List<Comment> findAll() throws IOException {
        InputStream resourceAsStream = null;

            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
            List<Comment> comments = commentMapper.selectAll();
            if (comments.size() > 10){
                comments = comments.subList(0, 10);
            }
            return comments;

    }

    /**
     * 将单个node添加到评链表中
     * @param firstList
     * @param commentNode
     * @return
     */
    private boolean addNode(List<CommentNode> firstList, CommentNode commentNode){
        for (CommentNode node : firstList) {
            //判断留言的上一条是否是这条留言（判断这条留言，是否是当前评论的回复）
            if (node.getCommentId().equals(commentNode.getCommentLastId())){
                node.getNextNode().add(commentNode);
                return true;
            }else{
                if (node.getNextNode().size() != 0){
                    //否则递归判断
                    if (addNode(node.getNextNode(),commentNode)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 将查出来的lastId不为null的回复都添加到第一层node集合中
     * @param firstList
     * @param thenList
     * @return
     */
    private List<CommentNode> addAllNode(List<CommentNode> firstList, List<CommentNode> thenList){
        while(thenList.size() != 0){
            int size = thenList.size();
            for (int i = 0; i < size; i++){
                if (addNode(firstList,new CommentNode(thenList.get(i)))){
                    thenList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        return firstList;
    }

    /**
     * 测试打印回复信息
     * @param list
     */
    private  void show(List<CommentNode> list){
        for (CommentNode commentNode : list) {
            System.out.println(commentNode.getCommentUserId()+"用户回复了"+ commentNode.getCommentLastId());
            if (commentNode.getNextNode().size() != 0){
                //递归打印
                show(commentNode.getNextNode());
            }
        }
    }


}
