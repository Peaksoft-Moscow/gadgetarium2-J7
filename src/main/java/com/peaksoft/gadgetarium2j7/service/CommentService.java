package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.CommentMapper;
import com.peaksoft.gadgetarium2j7.model.dto.CommentRequest;
import com.peaksoft.gadgetarium2j7.model.dto.CommentResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Comment;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.CommentRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void   add(CommentRequest request, Principal principal,Long idProduct){
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException(" User with id " + principal.getName() + " not found "));
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new EntityNotFoundException(" Product with id " + idProduct+ " not found "));
       Comment comment = commentMapper.mapToEntity(request);
       comment.setUser(user);
       comment.setProduct(product);
       commentRepository.save(comment);
    }

    public List<CommentResponse> getAll(){
        return  commentRepository.findAll()
                        .stream()
                        .map(commentMapper::mapToResponse).toList();
    }

    public void deleteById(Long id ){
        commentRepository.deleteById(id);
    }

    public CommentResponse update (Long id,CommentRequest request){
        Comment oldComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" User with id " + id + " not found "));
        oldComment.setRating(request.getRating());
        oldComment.setComment(request.getComment());
        oldComment.setImg(String.valueOf(request.getImg()));
        commentRepository.save(oldComment);
        return commentMapper.mapToResponse(oldComment);
    }
    public void deleteAll(Long idProduct ){
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException(" Comment with id " + idProduct + " not found "));
        List<Comment> comments= commentRepository.getAllComment(product.getId());
        commentRepository.deleteAll(comments);
    }
}
