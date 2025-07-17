package com.example.SnapHive.SnapHive.repository;

import com.example.SnapHive.SnapHive.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
