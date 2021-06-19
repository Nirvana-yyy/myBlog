package com.nirvana.domain.extend;

import com.nirvana.domain.Comment;
import com.nirvana.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YJL
 * 用来封装博客评论，采用链表结构
 */
@Data
/*@EqualsAndHashCode(callSuper = true)*/

public class CommentNode extends Comment {


    public CommentNode() {
    }

    /**
     * 评论的用户信息
     */
    private User user;

    /**
     * 下一条回复（节点）
     */
    private List<CommentNode> nextNode = new ArrayList<>();

    public CommentNode (CommentNode commentNode){
        super();
        setCommentId(commentNode.getCommentId());
        setCommentBlogId(commentNode.getCommentBlogId());
        setCommentContent(commentNode.getCommentContent());
        setCommentUserId(commentNode.getCommentUserId());
        setCommentLastId(commentNode.getCommentLastId());
        setCommentTime(commentNode.getCommentTime());
        setCommentLastNickname(commentNode.getCommentLastNickname());
        this.user = commentNode.getUser();
    }

    public void setComment(Comment comment) {
        super.setCommentId(comment.getCommentId());
        super.setCommentContent(comment.getCommentContent());
        super.setCommentBlogId(comment.getCommentBlogId());
        super.setCommentTime(comment.getCommentTime());
        super.setCommentLastId(comment.getCommentLastId());
        super.setCommentUserId(comment.getCommentUserId());

    }

    @Override
    public String toString() {
        return "CommentNode{" +
                super.toString()+
                "user=" + user +
                ", nextNode=" + nextNode +
                '}';
    }
}
