package com.example.BootApp.resources;

import com.example.BootApp.DTO.*;
import com.example.BootApp.models.Account;
import com.example.BootApp.models.Post;
import com.example.BootApp.models.WorkType;
import com.example.BootApp.repo.PeopleRepositorry;
import com.example.BootApp.repo.PostsRepository;
import com.example.BootApp.secutity.AccountAuthenticationProvider;
import com.example.BootApp.secutity.AccountDetails;
import com.example.BootApp.services.impl.PostServisImpl;
import com.example.BootApp.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("post")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST}, allowedHeaders = "*")
public class PostController {
    private final PostServisImpl postServisImpl;
    private final PostsRepository postsRepository;
    private final PeopleRepositorry peopleRepositorry;


    private final PostValidator postValidator;
    public static String UPLOAD_DIRECTORY = System.getProperty("BootApp/images") + "/uploads";
    @Autowired
    public PostController(PostServisImpl postServisImpl, PostsRepository postsRepository, PeopleRepositorry peopleRepositorry,  PostValidator postValidator) {
        this.postServisImpl = postServisImpl;

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


        postServisImpl.update(post.getId(),post);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("getWorkType")
    @ResponseBody
    public WorkType[] workType(){
        return WorkType.values();
    }


    @GetMapping("/getHeaders")
    @ResponseBody
    public ResponseEntity<List<PostHeaderDTO>> getHeaders(){
        return ResponseEntity.ok(postServisImpl.headers());
    }


    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Post>> all(){
        return ResponseEntity.ok(postsRepository.findAll());
    }



    @GetMapping("{id}")
    @ResponseBody
    public GetPostDTO show(@PathVariable("id") int id)   {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println(username);
            System.out.println( authentication.getPrincipal());

        }

    return postServisImpl.findOne(id) ;
    }

    @PostMapping("/upload")
    public String uploadImage( @RequestBody MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        return "imageupload/index";
    }







    @PostMapping("/del/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id)  {
        postServisImpl.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);


    }
    @ResponseBody
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody @Valid ValidatePostDTO post,
                                  BindingResult bindingResult){
        postValidator.validate(post,bindingResult);
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
    public ResponseEntity<?> save(@RequestBody @Valid AddPostDTO post,
                                             BindingResult bindingResult) throws JsonProcessingException {


        postServisImpl.save(post);
        return ResponseEntity.ok(HttpStatus.OK);


        //        postValidator.validate(post,bindingResult);
//        if (bindingResult.hasErrors()) {
//
//            List<ValidationErrorDTO> errors = bindingResult.getFieldErrors().stream()
//                    .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
//                    .collect(Collectors.toList());
//
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//
//
//        postServisImpl.save(post,post.getOwner());
//        return ResponseEntity.ok(HttpStatus.OK);

    }



    @ResponseBody
    @PostMapping("/testSave")
    public AddPostDTO testsave(@RequestBody  AddPostDTO addPostDTO,
                                  BindingResult bindingResult){

      //  System.out.println(addPostDTO.getPost_city());


        return addPostDTO;

    }
    @ResponseBody
    @GetMapping("/huj")
    public ResponseEntity<?> huk(@RequestBody  AddPostDTO addPostDTO,
                               BindingResult bindingResult){

        //  System.out.println(addPostDTO.getPost_city());


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
