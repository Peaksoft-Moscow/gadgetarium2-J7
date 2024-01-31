package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.CommentRequest;
import com.peaksoft.gadgetarium2j7.model.dto.CommentResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment mapToEntity(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setRating(commentRequest.getRating());
        comment.setComment(commentRequest.getComment());
        comment.setImg(commentRequest.getImg());
        return comment;
    }

    public CommentResponse mapToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .rating(comment.getRating())
                .comment(comment.getComment())
                .build();
    }
}
