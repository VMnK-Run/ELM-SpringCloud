package com.tju.elmcloud.service;

import com.tju.elmcloud.po.Comment;

import java.util.List;


public interface CommentService {
    public List<Comment> listComment(Comment comment);

    public int saveComment(Comment comment);

    public Comment getCommentById(Integer cmId);

    public int removeComment(Integer cmId);

    public int updateComment(Comment comment);
}
