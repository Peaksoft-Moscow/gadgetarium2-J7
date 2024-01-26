package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.CommentMapper;
import com.peaksoft.gadgetarium2j7.model.dto.CommentRequest;
import com.peaksoft.gadgetarium2j7.model.entities.Comment;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.CommentRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void   add(CommentRequest request, Principal principal){
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));

       Comment comment = commentMapper.mapToEntity(request);
       comment.setUser(user);
       commentRepository.save(comment);
    }
}
