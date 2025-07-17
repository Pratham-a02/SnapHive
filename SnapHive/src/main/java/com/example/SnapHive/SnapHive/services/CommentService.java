package com.example.SnapHive.SnapHive.services;

import com.example.SnapHive.SnapHive.entities.Comment;
import com.example.SnapHive.SnapHive.exceptions.CommentException;
import com.example.SnapHive.SnapHive.exceptions.PostException;
import com.example.SnapHive.SnapHive.exceptions.UserException;

public interface CommentService {

    public Comment createComment(Comment comment,Long userId,Long postId) throws UserException, PostException;

    public Comment findCommentById(Long commentId) throws CommentException;

    public Comment likeComment(Long commentId,Long userId) throws UserException,CommentException;

    public Comment unlikeComment(Long commentId,Long userId) throws UserException,CommentException;
}
