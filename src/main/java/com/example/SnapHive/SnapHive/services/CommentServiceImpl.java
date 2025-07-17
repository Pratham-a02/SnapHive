package com.example.SnapHive.SnapHive.services;

import com.example.SnapHive.SnapHive.entities.Comment;
import com.example.SnapHive.SnapHive.entities.Post;
import com.example.SnapHive.SnapHive.entities.User;
import com.example.SnapHive.SnapHive.exceptions.CommentException;
import com.example.SnapHive.SnapHive.exceptions.PostException;
import com.example.SnapHive.SnapHive.exceptions.UserException;
import com.example.SnapHive.SnapHive.repository.CommentRepository;
import com.example.SnapHive.SnapHive.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long userId, Long postId) throws UserException, PostException {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        Comment createdComment = commentRepository.save(comment);
        post.getComments().add(createdComment);
        postRepository.save(post);
        return createdComment;
    }

    @Override
    public Comment findCommentById(Long commentId) throws CommentException {
        return commentRepository.findById(commentId).orElseThrow(()-> new CommentException("Comment does not exists with id: " + commentId));
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws UserException, CommentException {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);
        comment.getLikedByUsers().add(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment unlikeComment(Long commentId, Long userId) throws UserException, CommentException {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);
        comment.getLikedByUsers().remove(user);
        return commentRepository.save(comment);
    }
}
