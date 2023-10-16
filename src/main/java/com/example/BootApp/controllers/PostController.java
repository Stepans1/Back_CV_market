package com.example.BootApp.controllers;

import com.example.BootApp.DTO.PostHeaderDTO;
import com.example.BootApp.DTO.ValidationErrorDTO;
import com.example.BootApp.models.Post;
import com.example.BootApp.models.WorkType;
import com.example.BootApp.repo.PeopleRepositorry;
import com.example.BootApp.repo.PostsRepository;
import com.example.BootApp.services.PostServis;
import com.example.BootApp.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("post")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST}, allowedHeaders = "*")
public class PostController {
    private final PostServis postServis;
    private final PostsRepository postsRepository;
    private final PeopleRepositorry peopleRepositorry;

    private final PostValidator postValidator;
    @Autowired
    public PostController(PostServis postServis, PostsRepository postsRepository, PeopleRepositorry peopleRepositorry, PostValidator postValidator) {
        this.postServis = postServis;

        this.postsRepository = postsRepository;
        this.peopleRepositorry = peopleRepositorry;
        this.postValidator = postValidator;
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseEntity<?> updatePost(@RequestBody @Valid Post post ,BindingResult result) {
        if (result.hasErrors()) {

            List<ValidationErrorDTO> errors = result.getFieldErrors().stream()
                    .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }


        postServis.update(post.getId(),post);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("getWorkType")
    @ResponseBody
    public WorkType[] workType(){
        return WorkType.values();
    }


    @GetMapping("/getHeaders")
    @ResponseBody
    public List<PostHeaderDTO> getHeaders(){
        return postServis.headers();
    }



    @GetMapping("/{id}")
    @ResponseBody
    public Post show(@PathVariable("id") int id)  {

        return postServis.findOne(id);
    }







    @PostMapping("/del/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id)  {
        postServis.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);


    }
    @ResponseBody
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody @Valid Post post,
                                  BindingResult bindingResult){
        postValidator.validate(post,bindingResult);
        System.out.println(post.getOwner().getId());
        if (bindingResult.hasErrors()) {

            List<ValidationErrorDTO> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }


        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid Post post,
                                             BindingResult bindingResult){
        postValidator.validate(post,bindingResult);
        System.out.println(post.getOwner().getId());
        if (bindingResult.hasErrors()) {

            List<ValidationErrorDTO> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }



        postServis.save(post,post.getOwner());
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<PostErrorResponse> handlException(PostNotFoundException e){
        PostErrorResponse response=new PostErrorResponse("Post with this id wasn't found",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    private ResponseEntity<PostErrorResponse> handlException(PostNotDeleted e){
        PostErrorResponse response=new PostErrorResponse("Post not deleted",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    private ResponseEntity<PostErrorResponse> handlException(PostNotCreatedException e){
        PostErrorResponse response=new PostErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
