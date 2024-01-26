package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.CommentRequest;
import com.peaksoft.gadgetarium2j7.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add/comment/{id}")
    public String add(@RequestBody CommentRequest commentRequest, Principal principal,@PathVariable("id") Long idProduct){
        commentService.add(commentRequest,principal,idProduct);
        return " Ваш отзыв успешно отпрален! ";
    }

}
