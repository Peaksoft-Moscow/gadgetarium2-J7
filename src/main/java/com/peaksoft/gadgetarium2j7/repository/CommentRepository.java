package com.peaksoft.gadgetarium2j7.repository;

import com.peaksoft.gadgetarium2j7.model.entities.Comment;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT c FROM Comment c join c.user u WHERE c.id = :id and u.email =: email")
    Comment findCommentById(@Param("id") Long id ,@Param("email") User user);
}
