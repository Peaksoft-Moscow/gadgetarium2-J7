package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.CommentRequest;
import com.peaksoft.gadgetarium2j7.model.dto.CommentResponse;
import com.peaksoft.gadgetarium2j7.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add/comment/{id}")
    @Operation(description = " This is method created comment by product. ")
    public String add(@RequestBody CommentRequest commentRequest, Principal principal,@PathVariable("id") Long idProduct){
        commentService.add(commentRequest,principal,idProduct);
        return " Ваш отзыв успешно отправлен! ";
    }

    @GetMapping("/get-all/comment")
    @Operation(description = " This is method get all comment. ")
    public List<CommentResponse> getAll (){
        return commentService.getAll();
    }

    @DeleteMapping("/delete-comment/{id}")
    @Operation(description = " This is method delete by id. ")
    public String deleteById(@PathVariable("id") Long id ){
        commentService.deleteById(id);
        return " Success deleted! ";
    }

    @PutMapping("/update/comment/{id}")
    @Operation(description = " This is method update comment. ")
    public  CommentResponse update (@PathVariable("id") Long id,
                                    @RequestBody CommentRequest commentRequest ){
       return commentService.update(id,commentRequest);
    }
    @DeleteMapping("/delete-All/{id}")
    @Operation(description = " This is method delete by id. ")
    public String deleteAll(@PathVariable("id") Long idProduct,Principal principal ){
        commentService.deleteAll(idProduct,principal);
        return " Success deleted All! ";
    }

}
